package com.self.blog.profile.web.service;

import com.self.blog.profile.application.usecase.VerifyNicknameUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VerifyNicknameProxyService {
    private final VerifyNicknameUseCase verifyNicknameUseCase;

    public boolean verifyNickname(String nickname) {
        return verifyNicknameUseCase.verifyNickname(nickname);
    }
}
