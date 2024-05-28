package com.self.blog.security.jwt.exception;

import com.self.blog.common.support.exception.CustomException;
import com.self.blog.common.support.exception.ErrorCode;

public class JwtException extends CustomException {
    public JwtException() {
        super();
    }

    public JwtException(String message) {
        super(message);
    }

    public JwtException(String message, Throwable cause) {
        super(message, cause);
    }

    public JwtException(ErrorCode errorCode) {
        super(errorCode);
    }

    public JwtException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    @Override
    public ErrorCode getErrorCode() {
        return super.getErrorCode();
    }
}
