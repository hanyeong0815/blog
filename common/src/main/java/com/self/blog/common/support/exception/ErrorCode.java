package com.self.music.common.support.exception;

import org.springframework.http.HttpStatus;

public interface ErrorCode { // public enum SomeErrorCode implements ErrorCode { … }
    String name(); // -> enum 클래스들은 모두 기본적으로 구현됨. (우리가 안 만듦.)
    HttpStatus defaultHttpStatus();
    String defaultMessage();
    RuntimeException defaultException();
    RuntimeException defaultException(Throwable cause);
}
