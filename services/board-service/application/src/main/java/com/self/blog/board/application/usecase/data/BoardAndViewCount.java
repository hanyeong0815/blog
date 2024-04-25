package com.self.blog.board.application.usecase.data;

import lombok.Builder;

import java.time.Instant;

public record BoardAndViewCount() {
    @Builder
    public record BoardAndViewCountResponse(
            String username,
            String boardId,
            String title,
            String content,
            Integer viewCount,
            Instant createdAt
    ) {}
}
