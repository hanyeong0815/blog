package com.self.blog.board.web.service;

import com.self.blog.board.application.usecase.ReplySaveUseCase;
import com.self.blog.board.domain.Reply;
import com.self.blog.board.web.dto.ReplySaveDto.ReplySaveRequestDto;
import com.self.blog.board.web.mapper.ReplyDtoMapper;
import com.self.blog.common.utils.time.ServerTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ReplySaveProxyService {
    private final ReplySaveUseCase replySaveUseCase;
    private final ReplyDtoMapper replyDtoMapper;

    private final ServerTime serverTime;

    public boolean saveReply(ReplySaveRequestDto dto) {
        Reply reply = replyDtoMapper.from(dto, new ArrayList<>(), false, serverTime.nowInstant());
        return replySaveUseCase.replySave(dto.targetId(), reply);
    }
}
