package com.self.blog.board.application.usecase.data;

import lombok.Builder;

import java.time.Instant;

public record BoardListView() {
    @Builder
    public record BoardListViewResponse(
            String boardId,
            String title,
            String username,
            Integer viewCount,
            Integer commentCount,
            Instant createdAt
    ) {}
}
