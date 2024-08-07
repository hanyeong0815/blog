package com.self.blog.email.exception;

import com.self.blog.common.support.exception.CustomException;
import com.self.blog.common.support.exception.ErrorCode;

public class EmailPostException extends CustomException {
    public EmailPostException() {
        super();
    }

    public EmailPostException(String message) {
        super(message);
    }

    public EmailPostException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailPostException(ErrorCode errorCode) {
        super(errorCode);
    }

    public EmailPostException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    @Override
    public ErrorCode getErrorCode() {
        return super.getErrorCode();
    }
}
