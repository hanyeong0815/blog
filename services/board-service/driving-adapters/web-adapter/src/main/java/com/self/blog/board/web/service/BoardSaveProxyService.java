package com.self.blog.board.web.service;

import com.self.blog.board.application.usecase.BoardSaveUseCase;
import com.self.blog.board.application.usecase.BoardViewSaveUseCase;
import com.self.blog.board.web.dto.BoardSaveDto.BoardSaveRequestDto;
import com.self.blog.board.web.dto.BoardSaveDto.BoardSaveResponseDto;
import com.self.blog.board.web.mapper.BoardDtoMapper;
import com.self.blog.common.utils.time.ServerTime;
import com.self.blog.board.domain.Board;
import com.self.blog.board.domain.BoardView;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardSaveProxyService {
    private final BoardSaveUseCase boardSaveUseCase;
    private final BoardViewSaveUseCase boardViewSaveUseCase;

    private final BoardDtoMapper boardDtoMapper;

    private final ServerTime serverTime;

    public BoardSaveResponseDto boardSave(BoardSaveRequestDto req) {
        Board board = boardDtoMapper.toDomain(req, serverTime.nowInstant());
        Board savedBoard = boardSaveUseCase.boardSave(board);

        BoardView boardView = BoardView.builder()
                .boardId(savedBoard.id)
                .viewCount(0)
                .build();
        BoardView savedBoardView = boardViewSaveUseCase.boardViewSave(boardView);

        return boardDtoMapper.toResponse(savedBoard, savedBoardView.viewCount);
    }
}
