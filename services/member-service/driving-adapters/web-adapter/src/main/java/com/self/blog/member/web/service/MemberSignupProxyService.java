package com.self.blog.member.web.service;

import com.self.blog.member.application.exception.MemberErrorCode;
import com.self.blog.member.application.usecase.MemberDeleteByExceptionUseCase;
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
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberSignupProxyService {
    private final MemberSignupUseCase memberSignupUseCase;
    private final MemberDeleteByExceptionUseCase memberDeleteByExceptionUseCase;
    private final MemberLoginUseCase memberLoginUseCase;

    private final MemberProfileInterfaceBlockingStub memberProfileInterfaceBlockingStub;

    private final MemberDtoMapper memberDtoMapper;

    public MemberLoginResponseDto signup(MemberSignupRequestDto dto) {
        // dto to domain
        Member member = memberDtoMapper.from(dto, MemberStatus.ACTIVE);
        Member savedMember = memberSignupUseCase.save(member);

        // domain에서 gRPC를 위한 형태로 변환
        MemberProfileSaveRequest memberProfileSaveRequest = memberDtoMapper.from(savedMember, dto);

        try {
            // gRPC통신으로 member-profile 송신
            memberProfileInterfaceBlockingStub.save(memberProfileSaveRequest);
        } catch (Exception e) {
            memberDeleteByExceptionUseCase.memberDeleteByException(savedMember.getId());
            throw MemberErrorCode.DEFAULT.defaultException();
        }


        JwtTokenPair jwtTokenPair = memberLoginUseCase.login(savedMember);
        return MemberLoginResponseDto.builder()
                .username(savedMember.getUsername())
                .nickname(dto.nickname())
                .jwtTokenPair(jwtTokenPair)
                .build();
    }
}
