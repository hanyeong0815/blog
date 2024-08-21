package com.self.blog.member.web.dto;

import jakarta.validation.constraints.NotBlank;

public record MemberLogoutDto() {
    public record MemberLogoutRequestDto(
            @NotBlank
            String refreshToken
    ) {}
}
