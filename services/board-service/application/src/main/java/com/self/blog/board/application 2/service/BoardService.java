package com.self.blog.board.application.service;

import com.self.blog.board.application.repository.BoardRepository;
import com.self.blog.board.application.usecase.BoardSaveUseCase;
import com.self.blog.board.domain.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService implements BoardSaveUseCase {
    private final BoardRepository boardRepository;

    @Override
    public Board boardSave(Board board) {
        return boardRepository.save(board);
    }
}
