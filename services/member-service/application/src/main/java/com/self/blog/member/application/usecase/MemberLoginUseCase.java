package com.self.blog.member.application.usecase;

import com.self.blog.member.application.usecase.data.JwtTokenPair;
import com.self.blog.member.domain.Member;
import org.springframework.security.core.Authentication;

public interface MemberLoginUseCase {
    JwtTokenPair login(Member member);
    JwtTokenPair login(Authentication authentication);
}
