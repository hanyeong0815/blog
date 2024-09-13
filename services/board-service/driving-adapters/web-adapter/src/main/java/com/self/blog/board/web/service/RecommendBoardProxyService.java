package com.self.blog.board.web.service;

import com.self.blog.board.application.usecase.RecommendBoardUseCase;
import com.self.blog.board.application.usecase.data.BoardListViewDto.BoardRecommendListView;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendBoardProxyService {
    private final RecommendBoardUseCase recommendBoardUseCase;

    public List<BoardRecommendListView> recommendBoard() throws IOException {
        return recommendBoardUseCase.recommendBoard();
    }
}
