package com.self.blog.member.web.dto;

public record PasswordUpdateDto() {
    public record PasswordUpdateRequestDto(
            String username,
            String password
    ) {}
}
