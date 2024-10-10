package com.self.blog.board.web.service;

import com.self.blog.board.application.usecase.BoardSaveUseCase;
import com.self.blog.board.application.usecase.data.BoardAndViewCount.BoardAndViewCountResponse;
import com.self.blog.board.web.dto.BoardSaveDto.BoardSaveRequestDto;
import com.self.blog.board.web.mapper.BoardDtoMapper;
import com.self.blog.common.utils.time.ServerTime;
import com.self.blog.board.domain.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class BoardSaveProxyService {
    private final BoardSaveUseCase boardSaveUseCase;

    private final BoardDtoMapper boardDtoMapper;

    private final ServerTime serverTime;

    public BoardAndViewCountResponse boardSave(BoardSaveRequestDto req, MultipartFile ogFile) {
        Board board = boardDtoMapper.toDomain(req, new ArrayList<>(), serverTime.nowInstant(), false);

        return boardSaveUseCase.boardSave(board, req.ogNumber(), ogFile);
    }
}
