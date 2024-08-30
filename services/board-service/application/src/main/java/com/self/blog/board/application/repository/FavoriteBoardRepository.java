package com.self.blog.board.application.repository;

import com.self.blog.board.domain.FavoriteBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface FavoriteBoardRepository {
    FavoriteBoard save(FavoriteBoard favoriteBoard);
    Optional<FavoriteBoard> findById(String id);
    Page<FavoriteBoard> findByUsername(String username, Pageable pageable);
    boolean existsByBoardIdAndUsername(String boardId, String username);
    void deleteByBoardIdAndUsername(String boardId, String username);
}
