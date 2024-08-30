package com.self.blog.board.web.dto;

import jakarta.validation.constraints.NotBlank;

public record ReplySaveDto() {
    public record ReplySaveRequestDto(
            @NotBlank
            String targetId,
            @NotBlank
            String username,
            @NotBlank
            String nickname,
            @NotBlank
            String content
    ) {}
}
