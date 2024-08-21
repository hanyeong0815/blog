package com.self.blog.board.application.service;

import com.self.blog.board.application.repository.FavoriteBoardRepository;
import com.self.blog.board.application.usecase.FavoriteBoardInsertOrDeleteUseCase;
import com.self.blog.board.application.usecase.FavoriteBoardIsPresentUseCase;
import com.self.blog.board.domain.FavoriteBoard;
import com.self.blog.common.utils.time.ServerTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FavoriteBoardService implements
        FavoriteBoardInsertOrDeleteUseCase,
        FavoriteBoardIsPresentUseCase
{
    private final FavoriteBoardRepository favoriteBoardRepository;

    private final ServerTime serverTime;

    @Override
    public boolean favoriteBoardIsPresent(String boardId, String username) {
        return favoriteBoardRepository.existsByBoardIdAndUsername(boardId, username);
    }

    @Override
    public boolean favoriteBoardInsertOrDelete(String boardId, String username) {
        boolean hasFavoriteBoard = this.favoriteBoardIsPresent(boardId, username);

        if (hasFavoriteBoard) {
            favoriteBoardRepository.deleteByBoardIdAndUsername(boardId, username);
            return false;
        }

        return favoriteBoardRepository.save(
                FavoriteBoard.builder()
                        .boardId(boardId)
                        .username(username)
                        .createdAt(serverTime.nowInstant())
                        .build()
        ) != null;
    }
}
