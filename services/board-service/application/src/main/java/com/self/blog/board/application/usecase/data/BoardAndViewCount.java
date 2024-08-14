package com.self.blog.board.application.usecase.data;

import com.self.blog.board.domain.Comment;
import lombok.Builder;

import java.time.Instant;
import java.util.List;

public record BoardAndViewCount() {
    @Builder
    public record BoardAndViewCountResponse(
            String username,
            String nickname,
            String category,
            String boardId,
            String title,
            String content,
            List<Comment> comments,
            Integer viewCount,
            Integer commentCount,
            Instant createdAt
    ) {}
}
