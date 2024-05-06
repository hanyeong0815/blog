package com.self.blog.board.application.repository;

import com.self.blog.board.domain.Board;

import java.util.Optional;

public interface BoardRepository {
    Board save(Board board);
    Optional<Board> findById(String id);
    Optional<Board> findByUsername(String username);
}
