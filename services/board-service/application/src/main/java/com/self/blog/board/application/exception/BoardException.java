package com.self.blog.board.application.exception;

import com.self.blog.common.support.exception.CustomException;
import com.self.blog.common.support.exception.ErrorCode;

public class BoardException extends CustomException {
    public BoardException() {
        super();
    }

    public BoardException(String message) {
        super(message);
    }

    public BoardException(String message, Throwable cause) {
        super(message, cause);
    }

    public BoardException(ErrorCode errorCode) {
        super(errorCode);
    }

    public BoardException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    @Override
    public ErrorCode getErrorCode() {
        return super.getErrorCode();
    }
}
