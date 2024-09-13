package com.self.blog.profile.web.dto;

public record CreateDomainDto() {
    public record CreateDomainRequestDto(
            String username,
            String domain
    ) {}
}
