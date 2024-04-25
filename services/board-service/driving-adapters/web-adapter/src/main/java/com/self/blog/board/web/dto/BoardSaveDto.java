package com.self.blog.board.web.dto;

import lombok.Builder;

public record BoardSaveDto() {
    @Builder
    public record BoardSaveRequestDto(
            String username,
            String title,
            String content
    ) {}
}
