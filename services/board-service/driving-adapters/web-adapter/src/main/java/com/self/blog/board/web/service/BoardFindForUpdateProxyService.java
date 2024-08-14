package com.self.blog.board.web.service;

import com.self.blog.board.application.usecase.BoardFindForUpdateUseCase;
import com.self.blog.board.application.usecase.data.BoardUpdateDto.BoardFindForUpdateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardFindForUpdateProxyService {
    private final BoardFindForUpdateUseCase boardFindForUpdateUseCase;

    public BoardFindForUpdateResponse boardFindForUpdateResponse(String boardId, String username) {
        return boardFindForUpdateUseCase.boardFindForUpdate(boardId, username);
    }
}
