package com.self.blog.board.mongo.repository;

import com.self.blog.board.mongo.entity.BoardDefaultSequenceEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BoardDefaultSequenceEntityRepository extends MongoRepository<BoardDefaultSequenceEntity, String> {
    Optional<BoardDefaultSequenceEntity> findFirstBy();
}
