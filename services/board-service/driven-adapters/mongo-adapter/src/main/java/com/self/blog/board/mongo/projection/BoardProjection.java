package com.self.blog.board.mongo.projection;

import java.time.Instant;

public record BoardProjection() {
    public record BoardListViewProjection(
            String id,
            String category,
            String title,
            String username,
            String nickname,
            Instant createdAt
    ) {}

    public record BoardFindForUpdateProjection(
            String title,
            String content,
            String category,
            String username
    ) {}
}
