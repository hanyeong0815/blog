package com.self.blog.board.web.dto;

public record ReplySaveDto() {
    public record ReplySaveRequestDto(
            String boardId,
            String username,
            String content
    ) {}
}
