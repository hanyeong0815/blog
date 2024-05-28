package com.self.blog.security.jwt.exception;

import com.self.blog.common.support.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum JwtErrorCode implements ErrorCode {
    INVALID_JWT_TOKEN("유효하지 않은 토큰입니다", HttpStatus.FORBIDDEN),
    EXPIRED_JWT_TOKEN("만료된 토큰입니다", HttpStatus.FORBIDDEN),
    UNSUPPORTED_JWT_TOKEN("지원하지 않는 토큰 유형입니다", HttpStatus.FORBIDDEN),
    CLAIMS_IS_EMPTY("권한 정보가 없는 토큰입니다.", HttpStatus.FORBIDDEN),
    DEFAULT("토큰 관련 오류", HttpStatus.FORBIDDEN);

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
    public JwtException defaultException() {
        return new JwtException(this);
    }

    @Override
    public JwtException defaultException(Throwable cause) {
        return new JwtException(this, cause);
    }
}
