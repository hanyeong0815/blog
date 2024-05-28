package com.self.blog.board.web.service;

import com.self.blog.board.application.usecase.CommentSaveUseCase;
import com.self.blog.board.domain.Comment;
import com.self.blog.board.web.dto.CommentSaveDto.CommentSaveRequestDto;
import com.self.blog.board.web.mapper.CommentDtoMapper;
import com.self.blog.common.utils.time.ServerTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CommentSaveProxyService {
    private final CommentSaveUseCase commentSaveUseCase;
    private final CommentDtoMapper commentDtoMapper;

    private final ServerTime serverTime;

    public Comment saveComment(CommentSaveRequestDto req) {
        Instant now = serverTime.nowInstant();

        Comment comment = commentDtoMapper.from(req, new ArrayList<>(), false, now);
        return commentSaveUseCase.saveComment(req.boardId(), comment);
    }
}
