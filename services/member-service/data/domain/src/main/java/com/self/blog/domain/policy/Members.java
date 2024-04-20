package com.self.blog.domain.policy;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public final class Members {
    public static class Validation {
        public static final String USERNAME = "^[a-z]+[a-z0-9]{3,30}$";
        public static final String PASSWORD = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,100}$";
    }

    public static class InvalidationMessage {
        public static final String USERNAME_MESSAGE = "아이디는 알파벳 소문자와 숫자를 사용하며, 숫자로 시작할 수 없습니다. (3~30자리)";
        public static final String PASSWORD_MESSAGE = "비밀번호는 영문, 숫자, 특수문자를 모두 사용하여 8자리 이상 100자리 이하입니다.";
    }

    /**
     * 계정 유효성 확인. 그냥 이런 것도 가능하다고 만든 것뿐이고, 실제로는 DTO 단계에서 대부분 유효성 처리되고 있어서 이런 거 안 맹글어도 됨.
     *
     * @param username the username to validate.
     * @return true if username is valid, and false if invalid.
     */
    public static boolean validateUsername(String username) {
        return Pattern.matches(Validation.USERNAME, username);
    }

    /**
     * 비밀번호 유효성 확인. 그냥 이런 것도 가능하다고 만든 것뿐이고, 실제로는 DTO 단계에서 대부분 유효성 처리되고 있어서 이런 거 안 맹글어도 됨.
     *
     * @param password the password to validate.
     * @return true if password is valid, and false if invalid.
     */
    public static boolean validatePassword(String password) {
        return Pattern.matches(Validation.PASSWORD, password);
    }
}