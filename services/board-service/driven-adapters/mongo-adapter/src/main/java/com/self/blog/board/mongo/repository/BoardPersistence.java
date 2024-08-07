package com.self.blog.board.mongo.repository;

import com.self.blog.board.application.repository.BoardRepository;
import com.self.blog.board.domain.Board;
import com.self.blog.board.mongo.entity.BoardEntity;
import com.self.blog.board.mongo.mapper.BoardEntityMapper;
import com.self.blog.board.readmodels.BoardReadModels.BoardListViewReadModels;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BoardPersistence implements BoardRepository {
    private final BoardMongoRepository repository;
    private final BoardEntityMapper mapper;

    @Override
    public Board save(Board board) {
        BoardEntity boardEntity = mapper.toEntity(board);
        return mapper.toDomain(
                repository.save(boardEntity)
        );
    }

    @Override
    public Optional<Board> findById(String id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<Board> findByUsername(String username) {
        return repository.findByUsername(username)
                .map(mapper::toDomain);
    }

    @Override
    public Page<BoardListViewReadModels> findAllBy(Pageable pageable) {
        return repository.findAllBy(pageable)
                .map(mapper::projectionToReadModel);
    }
}
