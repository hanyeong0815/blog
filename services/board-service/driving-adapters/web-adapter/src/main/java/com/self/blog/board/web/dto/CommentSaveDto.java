package com.self.blog.board.web.dto;

import jakarta.validation.constraints.NotBlank;

public record CommentSaveDto() {
    public record CommentSaveRequestDto(
            @NotBlank
            String boardId,
            @NotBlank
            String username,
            @NotBlank
            String nickname,
            @NotBlank
            String content
    ) {}
}
