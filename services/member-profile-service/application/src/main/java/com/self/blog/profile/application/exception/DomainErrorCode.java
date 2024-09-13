package com.self.blog.profile.application.exception;

import com.self.blog.common.support.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum DomainErrorCode implements ErrorCode {
    DOMAIN_ALREADY_USED("이미 사용중인 도메인입니다.", HttpStatus.BAD_REQUEST),
    DOMAIN_ALREADY_HAVE("이미 한개 이상의 도메인을 가지고 있습니다.", HttpStatus.BAD_REQUEST),
    DOMAIN_NOT_FOUND("도메인이 존재하지 않습니다.", HttpStatus.NOT_FOUND),
    DEFAULT("도메인 관련 오류", HttpStatus.INTERNAL_SERVER_ERROR);

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
    public DomainException defaultException() {
        return new DomainException(this);
    }

    @Override
    public DomainException defaultException(Throwable cause) {
        return new DomainException(this, cause);
    }
}
