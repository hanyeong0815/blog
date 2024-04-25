package com.self.blog.board.mongo.repository;

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
    public Optional<BoardView> findById(String boardId) {
        return repository.findById(boardId)
                .map(mapper::toDomain);
    }
}
