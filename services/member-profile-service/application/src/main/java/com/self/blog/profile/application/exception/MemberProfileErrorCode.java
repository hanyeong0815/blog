package com.self.blog.profile.application.exception;

import com.self.blog.common.support.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum MemberProfileErrorCode implements ErrorCode {
    NO_SUCH_USER("존재하지 않는 유저 정보입니다.", HttpStatus.BAD_REQUEST),
    DEFAULT("회원 정보 관련 오류", HttpStatus.INTERNAL_SERVER_ERROR);

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
    public RuntimeException defaultException() {
        return new MemberProfileException(this);
    }

    @Override
    public RuntimeException defaultException(Throwable cause) {
        return new MemberProfileException(this, cause);
    }
}
