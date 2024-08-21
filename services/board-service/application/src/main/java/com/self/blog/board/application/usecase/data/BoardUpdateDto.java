package com.self.blog.board.application.usecase.data;

import lombok.Builder;

public record BoardUpdateDto() {
    @Builder
    public record BoardFindForUpdateResponse(
            String title,
            String content,
            String category
    ) {}
}
