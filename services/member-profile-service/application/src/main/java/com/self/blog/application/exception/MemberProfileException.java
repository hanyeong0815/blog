package com.self.blog.application.exception;

import com.self.blog.common.support.exception.CustomException;
import com.self.blog.common.support.exception.ErrorCode;

public class MemberProfileException extends CustomException {
    public MemberProfileException() {
        super();
    }

    public MemberProfileException(String message) {
        super(message);
    }

    public MemberProfileException(String message, Throwable cause) {
        super(message, cause);
    }

    public MemberProfileException(ErrorCode errorCode) {
        super(errorCode);
    }

    public MemberProfileException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    @Override
    public ErrorCode getErrorCode() {
        return super.getErrorCode();
    }
}
