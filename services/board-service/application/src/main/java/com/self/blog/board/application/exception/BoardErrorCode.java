package com.self.blog.board.application.exception;

import com.self.blog.common.support.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum BoardErrorCode implements ErrorCode {
    BOARD_NOT_FOUND("존재하지 않는 게시글 입니다.", HttpStatus.NOT_FOUND),
    DEFAULT("게시판 관련 오류", HttpStatus.INTERNAL_SERVER_ERROR);

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
    public BoardException defaultException() {
        return new BoardException(this);
    }

    @Override
    public BoardException defaultException(Throwable cause) {
        return new BoardException(this, cause);
    }
}
