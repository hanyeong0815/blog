package com.self.blog.board.application.usecase;

public interface FavoriteBoardInsertOrDeleteUseCase {
    boolean favoriteBoardInsertOrDelete(String boardId, String username);
}
