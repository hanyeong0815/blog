package com.self.blog.board.application.usecase.data;

import com.self.blog.board.domain.Reply;
import lombok.Builder;

import java.time.Instant;
import java.util.List;

public record BoardAndViewCount() {
    @Builder
    public record BoardAndViewCountResponse(
            String username,
            String boardId,
            String title,
            String content,
            List<Reply> replies,
            Integer viewCount,
            Instant createdAt
    ) {}
}
