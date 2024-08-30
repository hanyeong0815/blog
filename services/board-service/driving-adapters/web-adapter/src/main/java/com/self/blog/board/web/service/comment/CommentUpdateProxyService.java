package com.self.blog.board.web.service.comment;

import com.self.blog.board.application.usecase.CommentUpdateUseCase;
import com.self.blog.board.domain.Comment;
import com.self.blog.board.web.dto.CommentUpdateDto.CommentUpdateRequestDto;
import com.self.blog.board.web.mapper.CommentDtoMapper;
import com.self.blog.common.utils.time.ServerTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentUpdateProxyService {
    private final CommentUpdateUseCase commentUpdateUseCase;
    private final CommentDtoMapper commentDtoMapper;

    private final ServerTime serverTime;

    public boolean commentUpdate(CommentUpdateRequestDto dto) {
        Comment comment = commentDtoMapper.from(dto, serverTime.nowInstant());

        return commentUpdateUseCase.commentUpdate(comment);
    }
}
