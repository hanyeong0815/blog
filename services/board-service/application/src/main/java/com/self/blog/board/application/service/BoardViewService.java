package com.self.blog.board.application.service;

import com.self.blog.board.application.repository.BoardViewRepository;
import com.self.blog.board.application.usecase.BoardViewSaveUseCase;
import com.self.blog.board.domain.BoardView;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardViewService implements BoardViewSaveUseCase {
    private final BoardViewRepository boardViewRepository;

    @Override
    public BoardView boardViewSave(BoardView boardView) {
        return boardViewRepository.save(boardView);
    }
}
