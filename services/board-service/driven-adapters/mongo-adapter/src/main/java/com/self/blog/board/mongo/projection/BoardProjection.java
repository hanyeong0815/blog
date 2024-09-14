package com.self.blog.board.mongo.projection;

import java.time.Instant;

public record BoardProjection() {
    public record BoardListViewProjection(
            String id,
            Long defaultSequence,
            Long categorySequence,
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

    public record BoardFindForElasticsearchProjection(
            String id,
            String boardDomain,
            String category,
            String username,
            String nickname,
            String title,
            String content,
            Instant createdAt,
            Boolean deleted
    ) {}
}
