package com.self.blog.member.web.dto;

public record JwtTokenDto() {
    public record RefreshJwtTokenRequestDto(String refreshToken) {}
}
