package com.self.blog.board.web.mapper;

import com.self.blog.board.domain.Reply;
import com.self.blog.board.web.dto.ReplySaveDto.ReplySaveRequestDto;
import org.mapstruct.Mapper;

import java.time.Instant;
import java.util.List;

@Mapper
public interface ReplyDtoMapper {
    Reply from(ReplySaveRequestDto dto, List<Reply> replies, boolean deleted, Instant createdAt);
}
