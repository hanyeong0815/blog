package com.self.blog.member.web.service;

import com.self.blog.member.application.usecase.CertifyInvitationCodeUseCase;
import com.self.blog.member.web.dto.InvitationCodeDto.CertifyInvitationCodeRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CertifyInvitationCodeProxyService {
    private final CertifyInvitationCodeUseCase certifyInvitationCodeUseCase;

    public boolean certifyInvitationCode(CertifyInvitationCodeRequestDto dto) {
        return certifyInvitationCodeUseCase.certifyInvitationCode(dto.invitationCode(), dto.email());
    }
}
