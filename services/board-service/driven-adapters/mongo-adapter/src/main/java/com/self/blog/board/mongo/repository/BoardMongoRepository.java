package com.self.blog.board.mongo.repository;

import com.self.blog.board.mongo.projection.BoardProjection.BoardFindForUpdateProjection;
import com.self.blog.board.mongo.projection.BoardProjection.BoardListViewProjection;
import com.self.blog.board.mongo.entity.BoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BoardMongoRepository extends MongoRepository<BoardEntity, String> {
    Optional<BoardEntity> findByUsername(String username);
    Page<BoardListViewProjection> findAllBy (Pageable pageable);
    Page<BoardListViewProjection> findByCategory(String Category, Pageable pageable);
    Optional<BoardFindForUpdateProjection> findProjectionsById(String id);
}
