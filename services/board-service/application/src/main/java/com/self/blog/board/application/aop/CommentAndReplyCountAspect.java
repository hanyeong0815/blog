package com.self.blog.board.application.aop;

import com.self.blog.board.application.exception.BoardErrorCode;
import com.self.blog.board.application.repository.BoardViewRepository;
import com.self.blog.board.domain.BoardView;
import com.self.blog.board.domain.Comment;
import com.self.blog.board.domain.Reply;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class CommentAndReplyCountAspect {
    private final BoardViewRepository boardViewRepository;

    @Around(value = "@annotation(CommentAndReplyCount) && args(boardId, comment)", argNames = "pjp,boardId,comment")
    public Object commentAndReplyCountUp(ProceedingJoinPoint pjp, String boardId, Comment comment) throws Throwable {
        Object result = pjp.proceed();

        BoardView boardView = boardViewRepository.findById(boardId).orElseThrow(BoardErrorCode.DEFAULT::defaultException);
        boardView.setCommentAndReplyCount(boardView.getCommentAndReplyCount() + 1);
        boardViewRepository.save(boardView);

        return result;
    }

    @Around(value = "@annotation(CommentAndReplyCount) && args(commentOrReplyId, reply)", argNames = "pjp,commentOrReplyId,reply")
    public Object commentAndReplyCountUp(ProceedingJoinPoint pjp, String commentOrReplyId, Reply reply) throws Throwable {
        Object result = pjp.proceed();

        String boardId = commentOrReplyId.substring(0, commentOrReplyId.indexOf("_"));

        BoardView boardView = boardViewRepository.findById(boardId).orElseThrow(BoardErrorCode.DEFAULT::defaultException);
        boardView.setCommentAndReplyCount(boardView.getCommentAndReplyCount() + 1);
        boardViewRepository.save(boardView);

        return result;
    }
}
