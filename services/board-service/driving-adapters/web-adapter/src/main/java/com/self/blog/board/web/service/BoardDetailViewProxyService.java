package com.self.blog.board.web.service;

import com.self.blog.board.application.usecase.BoardDetailViewUseCase;
import com.self.blog.board.application.usecase.data.BoardAndViewCount.BoardAndViewCountResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardDetailViewProxyService {
    private final BoardDetailViewUseCase boardDetailViewUseCase;

    public BoardAndViewCountResponse boardDetailView(String boardId, String username, HttpServletRequest httpServletRequest) {
        return boardDetailViewUseCase.boardDetailView(boardId, username, httpServletRequest.getRemoteAddr());
    }
}
