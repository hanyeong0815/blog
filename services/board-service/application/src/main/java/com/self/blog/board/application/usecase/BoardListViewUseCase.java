package com.self.blog.board.application.usecase;

import com.self.blog.board.application.usecase.data.BoardListView.BoardListViewResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardListViewUseCase {
    List<BoardListViewResponse> boardListView(Pageable pageable);
}
