package com.self.blog.board.mongo.repository;

import com.self.blog.board.application.repository.BoardRepository;
import com.self.blog.board.domain.Board;
import com.self.blog.board.domain.BoardElasticsearch;
import com.self.blog.board.mongo.entity.BoardEntity;
import com.self.blog.board.mongo.mapper.BoardEntityMapper;
import com.self.blog.board.readmodels.BoardReadModels.BoardFindForUpdateReadModel;
import com.self.blog.board.readmodels.BoardReadModels.BoardListViewReadModel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
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
    public List<Board> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<BoardElasticsearch> findAllByDeleted() {
        return repository.findByDeleted(false).stream()
                .map(mapper::projectionToDomain)
                .toList();
    }

    @Override
    public List<Board> findByIdIn(List<String> id) {
        return repository.findByIdIn(id).stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Board> findByUsername(String username) {
        return repository.findByUsername(username)
                .map(mapper::toDomain);
    }

    @Override
    public Page<BoardListViewReadModel> findAllBy(Pageable pageable) {
        return repository.findByDeleted(pageable, false)
                .map(mapper::projectionToReadModel);
    }

    @Override
    public Page<BoardListViewReadModel> findByCategory(String category, Pageable pageable) {
        return repository.findByCategoryAndDeleted(category, pageable, false)
                .map(mapper::projectionToReadModel);
    }

    @Override
    public Optional<BoardFindForUpdateReadModel> findByIdForUpdate(String boardId) {
        return repository.findProjectionsById(boardId)
                .map(mapper::projectionToReadModel);
    }

    @Override
    public boolean existsByIdAndUsername(String boardId, String username) {
        return repository.existsByIdAndUsername(boardId, username);
    }
}
