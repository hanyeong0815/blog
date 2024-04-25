package com.self.blog.board.web.mapper;

import com.self.blog.board.domain.Board;
import com.self.blog.board.web.dto.BoardSaveDto.BoardSaveRequestDto;
import com.self.blog.board.web.dto.BoardSaveDto.BoardSaveResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.Instant;

@Mapper
public interface BoardDtoMapper {
    @Mapping(target = "createdAt", source = "now")
    Board toDomain(BoardSaveRequestDto boardSaveRequestDto, Instant now);
    BoardSaveResponseDto toResponse(Board board, Integer viewCount);
}
