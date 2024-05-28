package com.self.blog.board.web.mapper;

import com.self.blog.board.domain.Comment;
import com.self.blog.board.domain.Reply;
import com.self.blog.board.web.dto.CommentSaveDto.CommentSaveRequestDto;
import org.mapstruct.Mapper;

import java.time.Instant;
import java.util.List;

@Mapper
public interface CommentDtoMapper {
    Comment from(CommentSaveRequestDto dto, List<Reply> replies, boolean isDeleted, Instant createdAt);
}
