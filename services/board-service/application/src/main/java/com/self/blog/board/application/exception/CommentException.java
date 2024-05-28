package com.self.blog.board.application.exception;

import com.self.blog.common.support.exception.CustomException;
import com.self.blog.common.support.exception.ErrorCode;

public class CommentException extends CustomException {
    public CommentException() {
        super();
    }

    public CommentException(String message) {
        super(message);
    }

    public CommentException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommentException(ErrorCode errorCode) {
        super(errorCode);
    }

    public CommentException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    @Override
    public ErrorCode getErrorCode() {
        return super.getErrorCode();
    }
}
