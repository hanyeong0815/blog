package com.self.blog.member.application.usecase;

import com.self.blog.member.application.usecase.data.JwtTokenPair;
import org.springframework.security.core.Authentication;

public interface MemberLoginUseCase {
    JwtTokenPair login(Authentication authentication);
}
