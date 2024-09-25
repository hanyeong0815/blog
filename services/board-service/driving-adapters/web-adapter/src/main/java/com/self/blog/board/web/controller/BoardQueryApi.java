package com.self.blog.board.web.controller;

import com.self.blog.board.application.usecase.data.BoardAndViewCount.BoardAndViewCountResponse;
import com.self.blog.board.application.usecase.data.BoardListViewDto.BoardListResponse;
import com.self.blog.board.application.usecase.data.BoardListViewDto.BoardRecommendListView;
import com.self.blog.board.application.usecase.data.BoardUpdateDto.BoardFindForUpdateResponse;
import com.self.blog.board.web.service.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("board")
@RequiredArgsConstructor
public class BoardQueryApi {
    private final BoardListViewProxyService boardListViewProxyService;
    private final BoardDetailViewProxyService boardDetailViewProxyService;
    private final BoardFindForUpdateProxyService boardFindForUpdateProxyService;
    private final FavoriteBoardIsPresentProxyService favoriteBoardIsPresentProxyService;
    private final RecommendBoardProxyService recommendBoardProxyService;

    @GetMapping("{domain}")
    public BoardListResponse boardListView(
            @PageableDefault(sort = "createdAt", direction = Direction.DESC)
            Pageable pageable,
            @PathVariable String domain
    ) {

        pageable = pageable.previousOrFirst();

        return boardListViewProxyService.boardListView(domain, pageable);
    }

    @GetMapping("{boardId}/{username}")
    public BoardAndViewCountResponse boardDetailView(
            @PathVariable(value = "boardId") String boardId,
            @PathVariable(value = "username") String username,
            HttpServletRequest httpServletRequest
    ) {
        return boardDetailViewProxyService.boardDetailView(boardId, username, httpServletRequest);
    }

    @GetMapping("update/{boardId}/{username}")
    public BoardFindForUpdateResponse boardFindForUpdate(
            @PathVariable(value = "boardId") String boardId,
            @PathVariable(value = "username") String username
    ) {
        return boardFindForUpdateProxyService.boardFindForUpdateResponse(boardId, username);
    }

    @GetMapping("favorite/{boardId}/{username}")
    public boolean favoriteBoardIsPresent(
            @PathVariable(value = "boardId") String boardId,
            @PathVariable(value = "username") String username
    ) {
        return favoriteBoardIsPresentProxyService.favoriteBoardIsPresent(boardId, username);
    }

    @GetMapping("recommend")
    public List<BoardRecommendListView> recommendBoard() throws IOException {
        return recommendBoardProxyService.recommendBoard();
    }
}
