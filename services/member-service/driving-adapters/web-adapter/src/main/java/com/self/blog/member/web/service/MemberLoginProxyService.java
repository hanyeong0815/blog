package com.self.blog.member.web.service;

import com.self.blog.member.application.usecase.GetNicknameUseCase;
import com.self.blog.member.application.usecase.MemberLoginUseCase;
import com.self.blog.member.application.usecase.data.JwtTokenPair;
import com.self.blog.member.web.dto.MemberLoginDto.MemberLoginResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberLoginProxyService {
    private final MemberLoginUseCase memberLoginUseCase;
    private final GetNicknameUseCase getNicknameUseCase;

    public MemberLoginResponseDto login(Authentication authentication) {
        JwtTokenPair jwtTokenPair = memberLoginUseCase.login(authentication);
        String nickname = getNicknameUseCase.getNicknameUseCase(authentication.getName());

        return MemberLoginResponseDto.builder()
                .username(authentication.getName())
                .nickname(nickname)
                .jwtTokenPair(jwtTokenPair)
                .build();
    }
}
