package com.self.blog.member.web.service;

import com.self.blog.member.application.usecase.RefreshJwtTokenUseCase;
import com.self.blog.member.application.usecase.data.JwtTokenPair;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshJwtTokenProxyService {
    private final RefreshJwtTokenUseCase refreshJwtTokenUseCase;

    public JwtTokenPair refreshJwtToken(String refreshToken) {
        return refreshJwtTokenUseCase.refreshJwtToken(refreshToken);
    }
}
