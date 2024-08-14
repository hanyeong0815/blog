package com.self.blog.board.application.repository;

import com.self.blog.board.domain.Board;
import com.self.blog.board.readmodels.BoardReadModels.BoardFindForUpdateReadModel;
import com.self.blog.board.readmodels.BoardReadModels.BoardListViewReadModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BoardRepository {
    Board save(Board board);
    Optional<Board> findById(String id);
    Optional<Board> findByUsername(String username);
    Page<BoardListViewReadModel> findAllBy(Pageable pageable);
    Page<BoardListViewReadModel> findByCategory(String category, Pageable pageable);
    Optional<BoardFindForUpdateReadModel> findByIdForUpdate(String boardId);
}
