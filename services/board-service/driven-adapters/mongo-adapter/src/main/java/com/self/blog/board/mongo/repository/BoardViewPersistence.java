package com.self.blog.board.mongo.repository;

import com.self.blog.board.application.exception.BoardErrorCode;
import com.self.blog.board.application.repository.BoardViewRepository;
import com.self.blog.board.domain.BoardView;
import com.self.blog.board.mongo.entity.BoardViewEntity;
import com.self.blog.board.mongo.mapper.BoardViewEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BoardViewPersistence implements BoardViewRepository {
    private final BoardViewMongoRepository repository;
    private final BoardViewEntityMapper mapper;

    @Override
    public BoardView save(BoardView boardView) {
        BoardViewEntity boardViewEntity = mapper.toEntity(boardView);
        return mapper.toDomain(
                repository.save(boardViewEntity)
        );
    }

    @Override
    public Optional<BoardView> findById(String boardViewId) {
        return repository.findById(boardViewId)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<BoardView> findByBoardId(String boardId) {
        return repository.findByBoardId(boardId)
                .map(mapper::toDomain);
    }

    @Override
    public void viewCountUp(String boardId) {
        BoardViewEntity boardViewEntity = repository.findById(boardId)
                .orElseThrow(BoardErrorCode.DEFAULT::defaultException);

        boardViewEntity.setViewCount(boardViewEntity.getViewCount() + 1);
        repository.save(boardViewEntity);
    }
}
