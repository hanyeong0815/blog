package com.self.blog.board.web.dto;

public record CategoryDto() {
    public record CategorySaveRequestDto(
            String category,
            Integer level
    ) {}
}
