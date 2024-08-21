package com.self.blog.board.readmodels;

import com.self.blog.board.domain.FavoriteBoard;

import java.util.List;

public record FavoriteBoardReadModel() {
    public record FavoriteBoardListReadModel(
            List<FavoriteBoard> favoriteBoardList,
            Long totalElements,
            Integer totalPages
    ) {}
}
