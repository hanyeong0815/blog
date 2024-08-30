package com.self.blog.member.web.service;

import com.self.blog.member.application.usecase.MemberLogoutUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberLogoutProxyService {
    private final MemberLogoutUseCase memberLogoutUseCase;

    public void logout(String refreshToken) {
        memberLogoutUseCase.logout(refreshToken);
    }
}
