package com.self.blog.board.readmodels;

import java.time.Instant;

public record BoardReadModels() {
    public record BoardListViewReadModels(
            String id,
            String title,
            String username,
            Instant createdAt
    ) {}
}
