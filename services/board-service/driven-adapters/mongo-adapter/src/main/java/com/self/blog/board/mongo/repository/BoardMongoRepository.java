package com.self.blog.board.mongo.repository;

import com.self.blog.board.mongo.BoardProjection.BoardListViewProjection;
import com.self.blog.board.mongo.entity.BoardEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface BoardMongoRepository extends MongoRepository<BoardEntity, String> {
    Optional<BoardEntity> findByUsername(String username);
    List<BoardListViewProjection> findAllBy (Pageable pageable);
}
