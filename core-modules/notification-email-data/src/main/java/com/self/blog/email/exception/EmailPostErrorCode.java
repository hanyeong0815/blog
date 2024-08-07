package com.self.blog.email.exception;

import com.self.blog.common.support.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum EmailPostErrorCode implements ErrorCode {
    TAG_REQUIRED("이메일 태그가 지정되어야 메일을 받을 대상을 지정할 수 있습니다.", HttpStatus.BAD_REQUEST),
    MESSAGING_EXCEPTION("", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String message;
    private final HttpStatus status;

    @Override
    public HttpStatus defaultHttpStatus() {
        return status;
    }

    @Override
    public String defaultMessage() {
        return message;
    }

    @Override
    public EmailPostException defaultException() {
        return new EmailPostException(this);
    }

    @Override
    public EmailPostException defaultException(Throwable cause) {
        return new EmailPostException(this, cause);
    }
}
