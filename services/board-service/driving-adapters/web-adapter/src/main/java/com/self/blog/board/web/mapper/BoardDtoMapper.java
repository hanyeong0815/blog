package com.self.blog.board.web.mapper;

import com.self.blog.board.domain.Board;
import com.self.blog.board.domain.Reply;
import com.self.blog.board.web.dto.BoardSaveDto.BoardSaveRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.Instant;
import java.util.List;

@Mapper
public interface BoardDtoMapper {
    @Mapping(target = "createdAt", source = "now")
    Board toDomain(BoardSaveRequestDto boardSaveRequestDto, List<Reply> replies, Instant now);
}
