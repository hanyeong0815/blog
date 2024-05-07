package com.self.blog.board.application.usecase;

import com.self.blog.board.application.usecase.data.BoardAndViewCount.BoardAndViewCountResponse;
import com.self.blog.board.domain.Board;

public interface BoardSaveUseCase {
    BoardAndViewCountResponse boardSave(String category, Board board);
}
