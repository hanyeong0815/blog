package com.self.blog.board.web.dto;

import jakarta.validation.constraints.NotBlank;

public record BoardDeleteDto() {
    public record BoardDeleteRequestDto(
            @NotBlank
            String boardId,
            @NotBlank
            String username
    ) {}
}
