package com.self.blog.board.application.repository;

import com.self.blog.board.domain.Board;

import java.io.IOException;
import java.util.List;

public interface BoardSearchRepository {
    void saveAll(List<Board> boardList);
    List<String> search() throws IOException;
}
