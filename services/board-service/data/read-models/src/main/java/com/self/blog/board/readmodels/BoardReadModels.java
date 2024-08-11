package com.self.blog.board.readmodels;

import java.time.Instant;

public record BoardReadModels() {
    public record BoardListViewReadModels(
            String id,
            String category,
            String title,
            String username,
            String nickname,
            Instant createdAt
    ) {}
}
