package com.self.blog.application.usecase.data;

import lombok.Builder;

@Builder
public record JwtTokenPair(
        String accessToken,
        String refreshToken
) {
}
