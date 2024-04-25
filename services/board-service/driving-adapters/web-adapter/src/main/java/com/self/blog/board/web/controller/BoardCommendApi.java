package com.self.blog.board.web.controller;

import com.self.blog.board.application.usecase.data.BoardAndViewCount.BoardAndViewCountResponse;
import com.self.blog.board.web.dto.BoardSaveDto.BoardSaveRequestDto;
import com.self.blog.board.web.service.BoardSaveProxyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("board")
@RequiredArgsConstructor
public class BoardCommendApi {
    private final BoardSaveProxyService boardSaveProxyService;

    @PostMapping("")
    public BoardAndViewCountResponse boardSave(@RequestBody BoardSaveRequestDto req) {
        return boardSaveProxyService.boardSave(req);
    }
}
