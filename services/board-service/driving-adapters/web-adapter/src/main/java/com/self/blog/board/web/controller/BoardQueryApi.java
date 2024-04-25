package com.self.blog.board.web.controller;

import com.self.blog.board.application.usecase.data.BoardAndViewCount.BoardAndViewCountResponse;
import com.self.blog.board.web.service.BoardDetailViewProxyService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("board")
@RequiredArgsConstructor
public class BoardQueryApi {
    private final BoardDetailViewProxyService boardDetailViewProxyService;

    @GetMapping("{boardId}/{username}")
    public BoardAndViewCountResponse boardDetailView(
            @PathVariable(value = "boardId") String boardId,
            @PathVariable(value = "username") String username,
            HttpServletRequest httpServletRequest
    ) {
        return boardDetailViewProxyService.boardDetailView(boardId, username, httpServletRequest);
    }
}
