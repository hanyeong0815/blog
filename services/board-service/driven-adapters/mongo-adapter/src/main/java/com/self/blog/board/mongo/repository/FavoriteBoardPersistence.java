package com.self.blog.board.mongo.repository;

import com.self.blog.board.application.repository.FavoriteBoardRepository;
import com.self.blog.board.domain.FavoriteBoard;
import com.self.blog.board.mongo.mapper.FavoriteBoardEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class FavoriteBoardPersistence implements FavoriteBoardRepository {
    private final FavoriteBoardMongoRepository repository;
    private final FavoriteBoardEntityMapper mapper;

    @Override
    public FavoriteBoard save(FavoriteBoard favoriteBoard) {
        return mapper.toDomain(
                repository.save(
                        mapper.toEntity(favoriteBoard)
                )
        );
    }

    @Override
    public Optional<FavoriteBoard> findById(String id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Page<FavoriteBoard> findByUsername(String username, Pageable pageable) {
        return repository.findByUsername(username, pageable)
                .map(mapper::toDomain);
    }

    @Override
    public boolean existsByBoardIdAndUsername(String boardId, String username) {
        return repository.existsByBoardIdAndUsername(boardId, username);
    }

    @Override
    public void deleteByBoardIdAndUsername(String boardId, String username) {
        repository.deleteByBoardIdAndUsername(boardId, username);
    }
}
