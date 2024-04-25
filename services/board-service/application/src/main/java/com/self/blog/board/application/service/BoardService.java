package com.self.blog.board.application.service;

import com.self.blog.board.application.aop.ViewCountUp;
import com.self.blog.board.application.exception.BoardErrorCode;
import com.self.blog.board.application.mapper.BoardMapper;
import com.self.blog.board.application.repository.BoardRepository;
import com.self.blog.board.application.repository.BoardViewRepository;
import com.self.blog.board.application.usecase.BoardDetailViewUseCase;
import com.self.blog.board.application.usecase.BoardSaveUseCase;
import com.self.blog.board.application.usecase.data.BoardAndViewCount.BoardAndViewCountResponse;
import com.self.blog.board.domain.Board;
import com.self.blog.board.domain.BoardView;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService implements BoardSaveUseCase, BoardDetailViewUseCase {
    private final BoardRepository boardRepository;
    private final BoardViewRepository boardViewRepository;

    private final BoardMapper boardMapper;

    @Override
    public BoardAndViewCountResponse boardSave(Board board) {
        Board savedBoard = boardRepository.save(board);

        BoardView boardView = BoardView.builder()
                .boardId(savedBoard.id)
                .viewCount(0)
                .build();
        BoardView savedBoardView = boardViewRepository.save(boardView);

        return boardMapper.from(savedBoard, savedBoardView);
    }

    @ViewCountUp
    @Override
    public BoardAndViewCountResponse boardDetailView(String boardId, String username, String ViewIp) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(
                        BoardErrorCode.BOARD_NOT_FOUND::defaultException
                );
        BoardView boardView = boardViewRepository.findById(board.id)
                .orElseThrow(
                        BoardErrorCode.DEFAULT::defaultException
                );

        return boardMapper.from(board, boardView);
    }
}
