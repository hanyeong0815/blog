package com.self.blog.board.application.usecase;

import com.self.blog.board.application.usecase.data.BoardListViewDto.BoardListResponse;
import org.springframework.data.domain.Pageable;

public interface BoardListViewUseCase {
    BoardListResponse boardListView(String domain, Pageable pageable);
}
