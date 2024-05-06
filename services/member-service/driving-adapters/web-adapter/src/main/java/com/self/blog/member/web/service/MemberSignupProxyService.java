package com.self.blog.member.web.service;

import com.self.blog.member.application.authentication.utils.CommonAuthenticationToken;
import com.self.blog.member.application.authentication.utils.UserAuthenticationToken;
import com.self.blog.member.application.usecase.MemberLoginUseCase;
import com.self.blog.member.application.usecase.MemberSignupUseCase;
import com.self.blog.member.application.usecase.data.JwtTokenPair;
import com.self.blog.member.domain.Member;
import com.self.blog.member.domain.type.MemberStatus;
import com.self.blog.member.web.dto.MemberSignupDto.MemberSignupRequestDto;
import com.self.blog.member.web.mapper.MemberDtoMapper;
import com.self.blog.profile.lib.MemberProfileInterfaceGrpc.MemberProfileInterfaceBlockingStub;
import com.self.blog.profile.lib.MemberProfileSaveRequest;
import com.self.blog.member.web.dto.MemberLoginDto.MemberLoginResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class MemberSignupProxyService {
    private final MemberSignupUseCase memberSignupUseCase;
    private final MemberLoginUseCase memberLoginUseCase;

    private final MemberProfileInterfaceBlockingStub memberProfileInterfaceBlockingStub;

    private final MemberDtoMapper memberDtoMapper;

    public MemberLoginResponseDto signup(MemberSignupRequestDto dto) {
        Member member = memberDtoMapper.from(dto, MemberStatus.ACTIVE);
        Member savedMember = memberSignupUseCase.save(member);

        MemberProfileSaveRequest memberProfileSaveRequest = memberDtoMapper.from(savedMember, dto);
        memberProfileInterfaceBlockingStub.save(memberProfileSaveRequest);

        Collection<? extends GrantedAuthority> roles = savedMember.roles.stream()
                .map(role -> role.value)
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