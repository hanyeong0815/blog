package com.self.blog.board.mongo.repository;

import com.self.blog.board.mongo.entity.FavoriteBoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FavoriteBoardMongoRepository extends MongoRepository<FavoriteBoardEntity, String> {
    Page<FavoriteBoardEntity> findByUsername(String username, Pageable pageable);
    boolean existsByBoardIdAndUsername(String boardId, String username);
    void deleteByBoardIdAndUsername(String boardId, String username);
}
