package com.self.blog.board.application.mapper;

import com.self.blog.board.application.usecase.data.BoardAndViewCount.BoardAndViewCountResponse;
import com.self.blog.board.domain.Board;
import com.self.blog.board.domain.BoardView;
import org.mapstruct.Mapper;

@Mapper
public interface BoardMapper {
    BoardAndViewCountResponse from(Board board, BoardView boardView);
}
