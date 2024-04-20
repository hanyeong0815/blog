package com.self.blog.web.service;

import com.self.blog.application.usecase.MemberLoginUseCase;
import com.self.blog.application.usecase.data.JwtTokenPair;
import com.self.blog.web.dto.MemberLoginDto.MemberLoginResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberLoginProxyService {
    private final MemberLoginUseCase memberLoginUseCase;

    public MemberLoginResponseDto login(Authentication authentication) {
        JwtTokenPair jwtTokenPair = memberLoginUseCase.login(authentication);
        return MemberLoginResponseDto.builder()
                .username(authentication.getName())
                .jwtTokenPair(jwtTokenPair)
                .build();
    }
}
