package com.self.blog.board.application.usecase;

import com.self.blog.board.application.usecase.data.BoardListViewDto.BoardRecommendListView;

import java.io.IOException;
import java.util.List;

public interface RecommendBoardUseCase {
    List<BoardRecommendListView> recommendBoard() throws IOException;
}
