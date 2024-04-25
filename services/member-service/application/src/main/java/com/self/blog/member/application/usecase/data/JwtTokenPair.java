package com.self.blog.member.application.usecase.data;

import lombok.Builder;

@Builder
public record JwtTokenPair(
        String accessToken,
        String refreshToken
) {
}
