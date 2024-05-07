package com.self.blog.board.application.exception;

import com.self.blog.common.support.exception.CustomException;
import com.self.blog.common.support.exception.ErrorCode;

public class CategoryException extends CustomException {
    public CategoryException() {
        super();
    }

    public CategoryException(String message) {
        super(message);
    }

    public CategoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public CategoryException(ErrorCode errorCode) {
        super(errorCode);
    }

    public CategoryException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    @Override
    public ErrorCode getErrorCode() {
        return super.getErrorCode();
    }
}
