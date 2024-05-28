package com.self.blog.board.application.exception;

import com.self.blog.common.support.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum CommentErrorCode implements ErrorCode {
    COMMENT_NOT_FOUND("댓글이 존재하지 않습니다.", HttpStatus.NOT_FOUND),
    DEFAULT("댓글 관련 오류", HttpStatus.INTERNAL_SERVER_ERROR);

    public final String message;
    public final HttpStatus status;

    @Override
    public HttpStatus defaultHttpStatus() {
        return status;
    }

    @Override
    public String defaultMessage() {
        return message;
    }

    @Override
    public CommentException defaultException() {
        return new CommentException(this);
    }

    @Override
    public CommentException defaultException(Throwable cause) {
        return new CommentException(this, cause);
    }
}
