package com.self.blog.board.web.controller;

import com.self.blog.board.application.usecase.data.BoardAndViewCount.BoardAndViewCountResponse;
import com.self.blog.board.domain.Reply;
import com.self.blog.board.web.dto.BoardSaveDto.BoardSaveRequestDto;
import com.self.blog.board.web.dto.ReplySaveDto.ReplySaveRequestDto;
import com.self.blog.board.web.service.BoardSaveProxyService;
import com.self.blog.board.web.service.ReplySaveProxyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("board")
@RequiredArgsConstructor
public class BoardCommendApi {
    private final BoardSaveProxyService boardSaveProxyService;
    private final ReplySaveProxyService replySaveProxyService;

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping("")
    public BoardAndViewCountResponse boardSave(@RequestBody BoardSaveRequestDto req) {
        return boardSaveProxyService.boardSave(req);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping("reply")
    public Reply replySave(@RequestBody ReplySaveRequestDto dto) {
        return replySaveProxyService.saveReply(dto);
    }
}
