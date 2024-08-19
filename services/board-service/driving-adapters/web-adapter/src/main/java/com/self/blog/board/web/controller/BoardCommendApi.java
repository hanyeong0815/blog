package com.self.blog.board.web.controller;

import com.self.blog.board.application.usecase.data.BoardAndViewCount.BoardAndViewCountResponse;
import com.self.blog.board.domain.Comment;
import com.self.blog.board.web.dto.BoardDeleteDto.BoardDeleteRequestDto;
import com.self.blog.board.web.dto.BoardSaveDto.BoardSaveRequestDto;
import com.self.blog.board.web.dto.CommentDeleteDto.CommentDeleteRequestDto;
import com.self.blog.board.web.dto.CommentSaveDto.CommentSaveRequestDto;
import com.self.blog.board.web.dto.CommentUpdateDto.CommentUpdateRequestDto;
import com.self.blog.board.web.dto.ReplySaveDto.ReplySaveRequestDto;
import com.self.blog.board.web.service.*;
import com.self.blog.board.web.service.comment.CommentDeleteProxyService;
import com.self.blog.board.web.service.comment.CommentSaveProxyService;
import com.self.blog.board.web.service.comment.CommentUpdateProxyService;
import com.self.blog.board.web.service.comment.ReplySaveProxyService;
import jakarta.validation.Valid;
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
    private final BoardDeleteProxyService boardDeleteProxyService;
    private final CommentUpdateProxyService commentUpdateProxyService;

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping("")
    public BoardAndViewCountResponse boardSave(@RequestBody BoardSaveRequestDto req) {
        return boardSaveProxyService.boardSave(req);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping("comment")
    public Comment commentSave(@RequestBody @Valid CommentSaveRequestDto dto) {
        return commentSaveProxyService.saveComment(dto);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping("reply")
    public boolean replySave(@RequestBody @Valid ReplySaveRequestDto dto) {
        return replySaveProxyService.saveReply(dto);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @DeleteMapping("")
    public boolean deleteBoard(@RequestBody @Valid BoardDeleteRequestDto dto) {
        return boardDeleteProxyService.boardDelete(dto.boardId(), dto.username());
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @DeleteMapping("comment")
    public Comment commentDelete(@RequestBody @Valid CommentDeleteRequestDto dto) {
        return commentDeleteProxyService.commentDelete(dto.commentId(), dto.username());
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PatchMapping("comment")
    public boolean commentUpdate(@RequestBody @Valid CommentUpdateRequestDto dto) {
        return commentUpdateProxyService.commentUpdate(dto);
    }
}
