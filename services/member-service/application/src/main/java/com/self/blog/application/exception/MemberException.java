package com.self.music.member.application.exception;

import com.self.music.common.support.exception.CustomException;
import com.self.music.common.support.exception.ErrorCode;

public class MemberException extends CustomException {
    public MemberException() {
        super();
    }

    public MemberException(String message) {
        super(message);
    }

    public MemberException(String message, Throwable cause) {
        super(message, cause);
    }

    public MemberException(ErrorCode errorCode) {
        super(errorCode);
    }

    public MemberException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }
}
