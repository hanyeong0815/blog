package com.self.blog.board.application.usecase;

public interface FavoriteBoardIsPresentUseCase {
    boolean favoriteBoardIsPresent(String boardId, String username);
}
