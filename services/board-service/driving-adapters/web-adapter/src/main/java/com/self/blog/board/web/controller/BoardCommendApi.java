package com.self.blog.board.web.controller;

import com.self.blog.board.application.usecase.data.BoardAndViewCount.BoardAndViewCountResponse;
import com.self.blog.board.domain.Comment;
import com.self.blog.board.web.dto.BoardSaveDto.BoardSaveRequestDto;
import com.self.blog.board.web.dto.CommentSaveDto.CommentSaveRequestDto;
import com.self.blog.board.web.dto.ReplySaveDto.ReplySaveRequestDto;
import com.self.blog.board.web.service.BoardSaveProxyService;
import com.self.blog.board.web.service.CommentDeleteProxyService;
import com.self.blog.board.web.service.CommentSaveProxyService;
import com.self.blog.board.web.service.ReplySaveProxyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("board")
@RequiredArgsConstructor
public class BoardCommendApi {
    private final BoardSaveProxyService boardSaveProxyService;
    private final CommentSaveProxyService commentSaveProxyService;
    private final ReplySaveProxyService replySaveProxyService;
    private final CommentDeleteProxyService commentDeleteProxyService;

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping("")
    public BoardAndViewCountResponse boardSave(@RequestBody BoardSaveRequestDto req) {
        return boardSaveProxyService.boardSave(req);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping("comment")
    public Comment commentSave(@RequestBody CommentSaveRequestDto dto) {
        return commentSaveProxyService.saveComment(dto);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping("reply")
    public boolean replySave(@RequestBody ReplySaveRequestDto dto) {
        return replySaveProxyService.saveReply(dto);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @DeleteMapping("comment/{commentId}")
    public Comment commentDelete(@PathVariable String commentId) {
        return commentDeleteProxyService.commentDelete(commentId);
    }
}
