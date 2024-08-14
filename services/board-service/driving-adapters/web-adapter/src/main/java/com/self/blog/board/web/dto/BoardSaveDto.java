package com.self.blog.board.web.dto;

import lombok.Builder;

public record BoardSaveDto() {
    @Builder
    public record BoardSaveRequestDto(
            String id,
            String username,
            String nickname,
            String category,
            String title,
            String content
    ) {}
}
