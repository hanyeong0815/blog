package com.self.blog.profile.mongo.projection;

import lombok.Builder;

import java.time.Instant;

public record ProfileLogProjections() {
    @Builder
    public record ProfileLogDetailViewProjection(
            String username,
            String remark,
            Instant createdAt
    ) {}
}
