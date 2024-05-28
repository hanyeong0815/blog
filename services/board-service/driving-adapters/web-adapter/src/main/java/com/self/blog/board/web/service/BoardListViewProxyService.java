package com.self.blog.board.web.service;

import com.self.blog.board.application.usecase.BoardListViewUseCase;
import com.self.blog.board.application.usecase.data.BoardListView.BoardListViewResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardListViewProxyService {
    private final BoardListViewUseCase boardListViewUseCase;

    public List<BoardListViewResponse> boardListView(Pageable pageable) {
        return boardListViewUseCase.boardListView(pageable);
    }
}
