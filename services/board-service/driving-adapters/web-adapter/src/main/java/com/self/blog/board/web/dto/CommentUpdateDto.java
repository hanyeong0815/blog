package com.self.blog.board.web.dto;

import jakarta.validation.constraints.NotBlank;

public record CommentUpdateDto() {
    public record CommentUpdateRequestDto(
            @NotBlank
            String commentId,
            @NotBlank
            String username,
            @NotBlank
            String content
    ) {}
}
