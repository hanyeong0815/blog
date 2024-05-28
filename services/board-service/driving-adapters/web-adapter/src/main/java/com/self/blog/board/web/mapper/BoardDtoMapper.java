package com.self.blog.board.web.mapper;

import com.self.blog.board.domain.Board;
import com.self.blog.board.domain.Comment;
import com.self.blog.board.web.dto.BoardSaveDto.BoardSaveRequestDto;
import org.mapstruct.Mapper;

import java.time.Instant;
import java.util.List;

@Mapper
public interface BoardDtoMapper {
    Board toDomain(BoardSaveRequestDto boardSaveRequestDto, List<Comment> comments, Instant createdAt);
}
