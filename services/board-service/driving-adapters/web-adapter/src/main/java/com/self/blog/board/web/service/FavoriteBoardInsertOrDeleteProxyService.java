package com.self.blog.board.web.service;

import com.self.blog.board.application.usecase.FavoriteBoardInsertOrDeleteUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FavoriteBoardInsertOrDeleteProxyService {
    private final FavoriteBoardInsertOrDeleteUseCase favoriteBoardInsertOrDeleteUseCase;

    public boolean favoriteBoardInsertOrDelete(String boardId, String username) {
        return favoriteBoardInsertOrDeleteUseCase.favoriteBoardInsertOrDelete(boardId, username);
    }
}
