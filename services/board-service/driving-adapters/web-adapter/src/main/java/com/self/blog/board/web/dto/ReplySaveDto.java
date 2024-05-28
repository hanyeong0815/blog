package com.self.blog.board.web.dto;

public record ReplySaveDto() {
    public record ReplySaveRequestDto(
            String targetId,
            String username,
            String content
    ) {}
}
