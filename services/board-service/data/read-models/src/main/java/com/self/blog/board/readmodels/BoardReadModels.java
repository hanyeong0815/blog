package com.self.blog.board.readmodels;

import java.time.Instant;

public record BoardReadModels() {
    public record BoardListViewReadModel(
            String id,
            Long defaultSequence,
            Long categorySequence,
            String category,
            String title,
            String username,
            String nickname,
            Instant createdAt
    ) {}

    public record BoardFindForUpdateReadModel(
            String title,
            String content,
            String category,
            String username
    ) {}
}
