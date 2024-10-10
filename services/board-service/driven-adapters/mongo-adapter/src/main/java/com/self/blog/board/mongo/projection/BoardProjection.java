package com.self.blog.board.mongo.projection;

import java.time.Instant;

public record BoardProjection() {
    public record BoardListViewProjection(
            String id,
            Long defaultSequence,
            Long domainSequence,
            String domain,
            String title,
            String content,
            String username,
            String nickname,
            Instant createdAt,
            String ogThumbnailFileName
    ) {}

    public record BoardFindForUpdateProjection(
            String title,
            String content,
            String domain,
            String username
    ) {}

    public record BoardFindForElasticsearchProjection(
            String id,
            String boardDomain,
            String domain,
            String username,
            String nickname,
            String title,
            String content,
            Instant createdAt,
            Boolean deleted
    ) {}
}
