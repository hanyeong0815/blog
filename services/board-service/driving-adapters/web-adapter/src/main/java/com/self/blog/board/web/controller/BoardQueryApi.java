package com.self.blog.board.web.controller;

import com.self.blog.board.application.usecase.data.BoardAndViewCount.BoardAndViewCountResponse;
import com.self.blog.board.application.usecase.data.BoardListView.BoardListViewResponse;
import com.self.blog.board.web.service.BoardDetailViewProxyService;
import com.self.blog.board.web.service.BoardListViewProxyService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("board")
@RequiredArgsConstructor
public class BoardQueryApi {
    private final BoardListViewProxyService boardListViewProxyService;
    private final BoardDetailViewProxyService boardDetailViewProxyService;

    @GetMapping("")
    public List<BoardListViewResponse> boardListView(@PageableDefault Pageable pageable) {
        pageable = pageable.previousOrFirst();
        return boardListViewProxyService.boardListView(pageable);
    }

    @GetMapping("{boardId}/{username}")
    public BoardAndViewCountResponse boardDetailView(
            @PathVariable(value = "boardId") String boardId,
            @PathVariable(value = "username") String username,
            HttpServletRequest httpServletRequest
    ) {
        return boardDetailViewProxyService.boardDetailView(boardId, username, httpServletRequest);
    }
}
