package com.self.blog.board.web.dto;

public record CommentSaveDto() {
    public record CommentSaveRequestDto(
            String boardId,
            String username,
            String nickname,
            String content
    ) {}
}
