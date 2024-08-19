package com.self.blog.board.web.mapper;

import com.self.blog.board.domain.Comment;
import com.self.blog.board.domain.Reply;
import com.self.blog.board.web.dto.CommentSaveDto.CommentSaveRequestDto;
import com.self.blog.board.web.dto.CommentUpdateDto.CommentUpdateRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.Instant;
import java.util.List;

@Mapper
public interface CommentDtoMapper {
    Comment from(CommentSaveRequestDto dto, List<Reply> replies, boolean isDeleted, Instant createdAt);
    @Mapping(target = "id", source = "dto.commentId")
    Comment from(CommentUpdateRequestDto dto, Instant createdAt);
}
