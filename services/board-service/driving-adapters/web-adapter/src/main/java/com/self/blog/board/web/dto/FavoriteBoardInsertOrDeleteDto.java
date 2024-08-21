package com.self.blog.board.web.dto;

import jakarta.validation.constraints.NotBlank;

public record FavoriteBoardInsertOrDeleteDto() {
    public record FavoriteBoardInsertOrDeleteRequestDto(
            @NotBlank
            String boardId,
            @NotBlank
            String username
    ) {}
}
