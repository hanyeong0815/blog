package com.self.blog.board.web.service;

import com.self.blog.board.application.usecase.ReplySaveUseCase;
import com.self.blog.board.domain.Reply;
import com.self.blog.board.web.dto.ReplySaveDto.ReplySaveRequestDto;
import com.self.blog.board.web.mapper.ReplyDtoMapper;
import com.self.blog.common.utils.time.ServerTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ReplySaveProxyService {
    private final ReplySaveUseCase replySaveUseCase;
    private final ReplyDtoMapper replyDtoMapper;

    private final ServerTime serverTime;

    public Reply saveReply(ReplySaveRequestDto req) {
        Instant now = serverTime.nowInstant();

        Reply reply = replyDtoMapper.from(req, new ArrayList<>(), now);
        return replySaveUseCase.saveReply(req.boardId(), reply);
    }
}
