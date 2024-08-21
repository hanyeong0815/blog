package com.self.blog.board.web.service;

import com.self.blog.board.application.usecase.BoardDeleteUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardDeleteProxyService {
    private final BoardDeleteUseCase boardDeleteUseCase;

    public boolean boardDelete(String boardId, String username) {
        return boardDeleteUseCase.boardDelete(boardId, username);
    }
}
