package com.self.blog.read_model;

import lombok.Builder;

import java.time.Instant;

public record ProfileLogReadModels() {
    @Builder
    public record ProfileLogDetailViewReadModel(
            String username,
            String remark,
            Instant createdAt
    ) {}
}
