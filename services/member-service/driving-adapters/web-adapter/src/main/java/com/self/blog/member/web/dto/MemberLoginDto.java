package com.self.blog.member.web.dto;

import com.self.blog.member.application.usecase.data.JwtTokenPair;
import lombok.Builder;

public record MemberLoginDto() {
    @Builder
    public record MemberLoginResponseDto(
            String username,
            JwtTokenPair jwtTokenPair
    ) {}
}
