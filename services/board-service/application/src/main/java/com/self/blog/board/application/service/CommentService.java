package com.self.blog.board.application.service;

import com.self.blog.board.application.aop.CommentAndReplyCount;
import com.self.blog.board.application.exception.BoardErrorCode;
import com.self.blog.board.application.exception.CommentErrorCode;
import com.self.blog.board.application.repository.BoardRepository;
import com.self.blog.board.application.repository.CommentRepository;
import com.self.blog.board.application.usecase.CommentDeleteUseCase;
import com.self.blog.board.application.usecase.CommentSaveUseCase;
import com.self.blog.board.application.usecase.CommentUpdateUseCase;
import com.self.blog.board.application.usecase.ReplySaveUseCase;
import com.self.blog.board.domain.Board;
import com.self.blog.board.domain.Comment;
import com.self.blog.board.domain.Reply;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static com.self.blog.common.utils.exception.Preconditions.validate;

@Service
@Log4j2
@RequiredArgsConstructor
public class CommentService implements
        CommentSaveUseCase,
        ReplySaveUseCase,
        CommentDeleteUseCase,
        CommentUpdateUseCase
{
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    private final Lock lock = new ReentrantLock();

    @CommentAndReplyCount
    @Override
    public Comment saveComment(String boardId, Comment comment) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(
                        BoardErrorCode.BOARD_NOT_FOUND::defaultException
                );

        if (board.getComments() == null) {
            board.setComments(new ArrayList<>());
        }

        comment.setId(
                createCommentOrReplyId(board.getComments().stream()
                        .map(Comment::getId).toList(), board.getId())
        );

        board.getComments().addLast(comment);
        boardRepository.save(board);

        return commentRepository.save(comment);
    }

    @CommentAndReplyCount // 댓글 수 AOP 연결을 위한 annotation
    @Override
    public boolean replySave(String commentOrReplyId, Reply reply) {
        // commentId의 구성 -> {boardId}_{comment index}
        // replyId의 구성 -> {commentId}_{reply index}
        String[] splitIdList = commentOrReplyId.split("_");
        String commentId = STR."\{splitIdList[0]}_\{splitIdList[1]}"; // commentId 추출

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(
                        CommentErrorCode.COMMENT_NOT_FOUND::defaultException
                );

        // id가 comment의 id일 경우
        if (splitIdList.length == 2) {
            reply.setId(
                    createCommentOrReplyId(comment.getReplies().stream()
                            .map(Reply::getId).toList(), commentId)
            );
            comment.getReplies().addLast(reply);
            return commentRepository.save(comment) != null;
        }

        return this.replyOfReplySave(comment, commentOrReplyId, reply);
//        return this.replyOfReplySave(comment, comment.replies, commentOrReplyId, reply) != null;
    }

    @Override
    public Comment deleteComment(String commentId, String username) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(
                        CommentErrorCode.COMMENT_NOT_FOUND::defaultException
                );

        validate(
                comment.getUsername().equals(username),
                CommentErrorCode.DEFAULT
        );

        comment.setUsername(null);
        comment.setNickname(null);
        comment.setContent("삭제된 댓글입니다.");
        comment.setDeleted(true);

        return commentRepository.save(comment);
    }

    // Queue를 사용한 경우
    // 큐를 사용하므로 스택 오버플로우의 위험이 없다. 따라서 댓글이 많더라도 안정적이다.
    // 댓글의 수가 적더라도 재귀함수와 비교했을 때 큰 성능차이는 없다.
    private boolean replyOfReplySave(Comment comment, String targetId, Reply reply) {
        Queue<Reply> queue = new LinkedList<>(comment.getReplies());

        while (!queue.isEmpty()) {
            Reply current = queue.poll();
            if (Objects.equals(current.getId(), targetId)) {
                reply.setId(
                        createCommentOrReplyId(current.getReplies().stream()
                                .map(Reply::getId).toList(), targetId)
                );
                current.getReplies().addLast(reply);

                return commentRepository.save(comment) != null;
            }

            queue.addAll(current.getReplies());
        }

        throw CommentErrorCode.COMMENT_NOT_FOUND.defaultException();
    }

    // 재귀함수를 사용한 경우
    // 탭스가 깊어질수록 스택 오버플로우의 위험이 높아진다.
    // 탭스가 적을수록 스택 오버플로우의 위험은 낮고 효율적이다.
//    private Reply replyOfReplySave(Comment comment, List<Reply> replies, String targetId, Reply reply) {
//        for (Reply rep : replies) {
//            if (Objects.equals(rep.id, targetId)) {
//                reply.id = STR."\{rep.id}_\{rep.replies.size()}";
//                rep.replies.addLast(reply);
//
//                commentRepository.save(comment);
//                return rep;
//            }
//
//            Reply nextDepth = replyOfReplySave(comment, rep.replies, targetId, reply);
//            if (nextDepth != null) {
//                return nextDepth;
//            }
//        }
//
//        return null;
//    }

    // 동시성 보장을 위한 방벙 { synchronized, lock }
    // synchronized: 사용하기 간단하나 단일 스레드만 접근할 수 있어 성능저하가 발생할 수 있음. 또한 synchronized block을 사용할 경우 block에 접근 탈출 자체에 리소스를 소비
    // lock: reentrantLock안에 다양한 기능을 사용할 수 있음. 대신 unlock을 반드시 명시해야하는 단점이 있음
    // 그 이외의 방법들 { java: { atomic, concurrent }, spring: { @Async, @Scheduled, Executor, JPA: { @Transactional } }, ... }
    // TODO 우선 Lock을 사용했으나 좀 더 실험 후 그대로 적용 혹은 변경
    private String createCommentOrReplyId(List<String> targetList, String targetId) {
        lock.lock();
        try {
            if (targetList.isEmpty()) {
                return STR."\{targetId}_0" ;
            }

            String[] splitIds = targetList.getLast().split("_");
            int newIdNumber = Integer.parseInt(splitIds[splitIds.length - 1]) + 1;
            StringBuilder newId = new StringBuilder();
            for (int i = 0; i < splitIds.length - 1; i++) {
                newId.append(splitIds[i]).append("_");
            }
            newId.append(newIdNumber);

            return newId.toString();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean commentUpdate(Comment comment) {
        Comment findComment = commentRepository.findById(comment.getId())
                .orElseThrow(
                        CommentErrorCode.COMMENT_NOT_FOUND::defaultException
                );

        validate(
                findComment.getUsername().equals(comment.getUsername()),
                CommentErrorCode.DEFAULT
        );

        Comment cloneComment = findComment.toBuilder()
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .build();

        return commentRepository.save(cloneComment) != null;
    }
}
