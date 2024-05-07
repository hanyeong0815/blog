package com.self.blog.board.application.exception;

import com.self.blog.common.support.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum CategoryErrorCode implements ErrorCode {
    CATEGORY_ALREADY_USED("이미 존재하는 카테고리입니다.", HttpStatus.IM_USED),
    CATEGORY_NOT_FOUND("존재하지않는 카테고리입니다", HttpStatus.NOT_FOUND),
    DEFAULT("카테고리 관련 오류", HttpStatus.INTERNAL_SERVER_ERROR);

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
    public CategoryException defaultException() {
        return new CategoryException(this);
    }

    @Override
    public CategoryException defaultException(Throwable cause) {
        return new CategoryException(this, cause);
    }
}
