package com.self.blog.board.application.service;

import com.self.blog.board.application.aop.CommentAndReplyCount;
import com.self.blog.board.application.exception.BoardErrorCode;
import com.self.blog.board.application.exception.CommentErrorCode;
import com.self.blog.board.application.repository.BoardRepository;
import com.self.blog.board.application.repository.CommentRepository;
import com.self.blog.board.application.usecase.CommentSaveUseCase;
import com.self.blog.board.application.usecase.ReplySaveUseCase;
import com.self.blog.board.domain.Board;
import com.self.blog.board.domain.Comment;
import com.self.blog.board.domain.Reply;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CommentService implements
        CommentSaveUseCase,
        ReplySaveUseCase
{
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    @CommentAndReplyCount
    @Override
    public Comment saveComment(String boardId, Comment comment) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(
                        BoardErrorCode.BOARD_NOT_FOUND::defaultException
                );

        if (board.comments == null) {
            board.comments = new ArrayList<>();
        }

        comment.id = createCommentOrReplyId(board.comments.stream().map(savedComment -> savedComment.id).toList(), board.id);
        board.comments.addLast(comment);
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
            reply.id = createCommentOrReplyId(comment.replies.stream().map(savedReply -> savedReply.id).toList(), commentId);
            comment.replies.addLast(reply);
            return commentRepository.save(comment) != null;
        }

        return this.replyOfReplySave(comment, commentOrReplyId, reply);
//        return this.replyOfReplySave(comment, comment.replies, commentOrReplyId, reply) != null;
    }

    // Queue를 사용한 경우
    // 큐를 사용하므로 스택 오버플로우의 위험이 없다. 따라서 댓글이 많더라도 안정적이다.
    // 댓글의 수가 적더라도 재귀함수와 비교했을 때 큰 성능차이는 없다.
    private boolean replyOfReplySave(Comment comment, String targetId, Reply reply) {
        Queue<Reply> queue = new LinkedList<>(comment.replies);

        while (!queue.isEmpty()) {
            Reply current = queue.poll();
            if (Objects.equals(current.id, targetId)) {
                reply.id = createCommentOrReplyId(current.replies.stream().map(currentReply -> currentReply.id).toList(), targetId);
                current.replies.addLast(reply);

                return commentRepository.save(comment) != null;
            }

            queue.addAll(current.replies);
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

    private String createCommentOrReplyId(List<String> targetList, String targetId) {
        if (targetList.isEmpty()) {
            return STR."\{targetId}_0";
        }

        String[] splitIds = targetList.getLast().split("_");
        int newIdNumber = Integer.parseInt(splitIds[splitIds.length - 1] + 1);
        StringBuilder newId = new StringBuilder();
        for (int i = 0; i < splitIds.length - 1; i++) {
            newId.append(splitIds[i]).append("_");
        }
        newId.append(newIdNumber);

        return newId.toString();
    }
}
