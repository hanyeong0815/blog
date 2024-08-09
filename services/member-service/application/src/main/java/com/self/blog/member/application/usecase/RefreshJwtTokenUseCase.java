package com.self.blog.member.application.usecase;

import com.self.blog.member.application.usecase.data.JwtTokenPair;

public interface RefreshJwtTokenUseCase {
    JwtTokenPair refreshJwtToken(String refreshToken);
}
