package com.self.blog.board.mongo.repository;

import com.self.blog.board.mongo.entity.BoardViewEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BoardViewMongoRepository extends MongoRepository<BoardViewEntity, String> {
    Optional<BoardViewEntity> findByBoardId(String boardId);
}
