package com.self.blog.board.mongo.projection;

import com.self.blog.board.mongo.entity.FavoriteBoardEntity;

import java.util.List;

public record FavoriteBoardProjection() {
    public record FavoriteBoardListProjection(
            List<FavoriteBoardEntity> content,
            Long totalElements,
            Integer totalPages
    ) {}
}
