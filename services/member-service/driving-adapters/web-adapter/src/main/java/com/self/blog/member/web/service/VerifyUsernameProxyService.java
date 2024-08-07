package com.self.blog.member.web.service;

import com.self.blog.member.application.usecase.VerifyUsernameUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VerifyUsernameProxyService {
    private final VerifyUsernameUseCase verifyUsernameUseCase;

    public boolean verifyUsername(String username) {
        return verifyUsernameUseCase.verifyUsername(username);
    }
}
