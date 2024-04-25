package com.self.blog.board.web.dto;

import lombok.Builder;

import java.time.Instant;

public record BoardSaveDto() {
    @Builder
    public record BoardSaveRequestDto(
            String username,
            String title,
            String content
    ) {}

    @Builder
    public record BoardSaveResponseDto(
            String username,
            String title,
            String content,
            Integer viewCount,
            Instant createdAt
    ) {}
}
