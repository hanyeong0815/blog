package com.self.blog.board.web.service;

import com.self.blog.board.application.usecase.FavoriteBoardIsPresentUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FavoriteBoardIsPresentProxyService {
    private final FavoriteBoardIsPresentUseCase favoriteBoardIsPresentUseCase;

    public boolean favoriteBoardIsPresent(String boardId, String username) {
        return favoriteBoardIsPresentUseCase.favoriteBoardIsPresent(boardId, username);
    }
}
