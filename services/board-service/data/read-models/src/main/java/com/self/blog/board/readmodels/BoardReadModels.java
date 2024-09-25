package com.self.blog.board.readmodels;

import java.time.Instant;

public record BoardReadModels() {
    public record BoardListViewReadModel(
            String id,
            Long defaultSequence,
            Long domainSequence,
            String domain,
            String title,
            String content,
            String username,
            String nickname,
            Instant createdAt
    ) {}

    public record BoardFindForUpdateReadModel(
            String title,
            String content,
            String domain,
            String username
    ) {}
}
