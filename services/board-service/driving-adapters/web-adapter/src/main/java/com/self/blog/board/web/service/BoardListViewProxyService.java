package com.self.blog.board.web.service;

import com.self.blog.board.application.usecase.BoardListViewUseCase;
import com.self.blog.board.application.usecase.data.BoardListViewDto.BoardListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardListViewProxyService {
    private final BoardListViewUseCase boardListViewUseCase;

    public BoardListResponse boardListView(Pageable pageable) {
        return boardListViewUseCase.boardListView(pageable);
    }
}
