package com.self.blog.application.usecase.data;

import java.time.Instant;

public record JwtToken(
        String accessToken,
        String refreshToken,
        Instant accessTokenExpiredAt,
        Instant refreshTokenExpiredAt
) {
}
