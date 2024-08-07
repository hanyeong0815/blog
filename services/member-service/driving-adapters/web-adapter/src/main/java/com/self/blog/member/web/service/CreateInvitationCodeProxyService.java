package com.self.blog.member.web.service;

import com.self.blog.member.application.usecase.CreateInvitationCodeUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateInvitationCodeProxyService {
    private final CreateInvitationCodeUseCase createInvitationCodeUseCase;

    public boolean createInvitationCode(String email) {
        return createInvitationCodeUseCase.createInvitationCode(email);
    }
}
