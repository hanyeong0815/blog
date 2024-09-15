package com.self.blog.board.web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

public record BoardSaveDto() {
    @Builder
    public record BoardSaveRequestDto(
            String id,
            @NotBlank
            String domain,
            @NotBlank
            String username,
            @NotBlank
            String nickname,
            @NotBlank
            String title,
            @NotBlank
            String content
    ) {}
}
