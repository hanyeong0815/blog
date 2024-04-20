package com.self.blog.application.usecase;

import com.self.blog.application.usecase.data.JwtTokenPair;
import org.springframework.security.core.Authentication;

public interface MemberLoginUseCase {
    JwtTokenPair login(Authentication authentication);
}
