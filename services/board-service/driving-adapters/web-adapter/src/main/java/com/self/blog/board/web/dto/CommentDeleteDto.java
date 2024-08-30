package com.self.blog.board.web.dto;

import jakarta.validation.constraints.NotBlank;

public record CommentDeleteDto() {
    public record CommentDeleteRequestDto(
            @NotBlank
            String commentId,
            @NotBlank
            String username
    ) {}
}
