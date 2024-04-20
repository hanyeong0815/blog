package com.self.blog.web.dto;

import com.self.blog.application.usecase.data.JwtTokenPair;
import lombok.Builder;

public record MemberLoginDto() {
    @Builder
    public record MemberLoginResponseDto(
            String username,
            JwtTokenPair jwtTokenPair
    ) {}
}
