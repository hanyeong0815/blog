package com.self.blog.board.mongo;

import java.time.Instant;

public record BoardProjection() {
    public record BoardListViewProjection(
            String id,
            String category,
            String title,
            String username,
            Instant createdAt
    ) {}
}
