package com.self.blog.board.application.usecase.data;

import lombok.Builder;

import java.time.Instant;
import java.util.List;

public record BoardListViewDto() {
    @Builder
    public record BoardListResponse(
            List<BoardListView> boardList,
            Integer totalPage,
            Long totalElement
    ) {}

    @Builder
    public record BoardListView (
            String boardId,
            String category,
            String title,
            String username,
            String nickname,
            Integer viewCount,
            Integer commentCount,
            Instant createdAt
    ) {}
}
