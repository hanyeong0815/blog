package com.self.blog.web.service;

import com.self.blog.application.authentication.utils.CommonAuthenticationToken;
import com.self.blog.application.authentication.utils.UserAuthenticationToken;
import com.self.blog.application.usecase.MemberLoginUseCase;
import com.self.blog.application.usecase.MemberSignupUseCase;
import com.self.blog.application.usecase.data.JwtTokenPair;
import com.self.blog.domain.Member;
import com.self.blog.domain.type.MemberStatus;
import com.self.blog.web.dto.MemberLoginDto.MemberLoginResponseDto;
import com.self.blog.web.dto.MemberSignupDto.MemberSignupRequestDto;
import com.self.blog.web.mapper.MemberDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public final class MemberSignupProxyService {
    private final MemberSignupUseCase memberSignupUseCase;
    private final MemberLoginUseCase memberLoginUseCase;

    private final MemberDtoMapper memberDtoMapper;

    public MemberLoginResponseDto signup(MemberSignupRequestDto dto) {
        Member member = memberDtoMapper.from(dto, MemberStatus.ACTIVE);
        Member savedMember = memberSignupUseCase.save(member);
        Collection<? extends GrantedAuthority> roles = savedMember.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .toList();
        Authentication auth = CommonAuthenticationToken.authenticated(UserAuthenticationToken.class, savedMember.username, savedMember.password, roles);
        JwtTokenPair jwtTokenPair = memberLoginUseCase.login(auth);
        return MemberLoginResponseDto.builder()
                .username(auth.getName())
                .jwtTokenPair(jwtTokenPair)
                .build();
    }
}
