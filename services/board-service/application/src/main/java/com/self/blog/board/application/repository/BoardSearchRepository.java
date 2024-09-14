package com.self.blog.board.application.repository;

import com.self.blog.board.application.usecase.data.BoardListViewDto.BoardRecommendListView;
import com.self.blog.board.domain.BoardElasticsearch;

import java.io.IOException;
import java.util.List;

public interface BoardSearchRepository {
    void saveAll(List<BoardElasticsearch> boardList);
    List<BoardRecommendListView> search() throws IOException;
}
