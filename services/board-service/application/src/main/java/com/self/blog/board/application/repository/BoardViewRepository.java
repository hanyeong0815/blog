package com.self.blog.board.application.repository;

import com.self.blog.board.domain.BoardView;

import java.util.Optional;

public interface BoardViewRepository {
    BoardView save(BoardView boardView);
    Optional<BoardView> findById(String boardViewId);
    Optional<BoardView> findByBoardId(String boardId);
    void viewCountUp(String boardId);
}
