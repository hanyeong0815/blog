package com.self.blog.board.application.service;

import com.self.blog.board.application.repository.BoardRepository;
import com.self.blog.board.application.usecase.ReplySaveUseCase;
import com.self.blog.board.domain.Reply;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReplyService implements ReplySaveUseCase {
    private final BoardRepository boardRepository;

    @Override
    public Reply saveReply(String boardId, Reply reply) {
        return boardRepository.saveReply(boardId, reply);
    }
}
