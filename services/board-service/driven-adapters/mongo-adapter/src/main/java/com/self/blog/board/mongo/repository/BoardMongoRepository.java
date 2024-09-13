package com.self.blog.board.mongo.repository;

import com.self.blog.board.mongo.projection.BoardProjection.BoardFindForUpdateProjection;
import com.self.blog.board.mongo.projection.BoardProjection.BoardListViewProjection;
import com.self.blog.board.mongo.entity.BoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface BoardMongoRepository extends MongoRepository<BoardEntity, String> {
    Optional<BoardEntity> findByUsername(String username);
    List<BoardEntity> findByIdIn(List<String> id);
    Page<BoardListViewProjection> findByDeleted (Pageable pageable, boolean deleted);
    Page<BoardListViewProjection> findByCategoryAndDeleted(String Category, Pageable pageable, boolean deleted);
    Optional<BoardFindForUpdateProjection> findProjectionsById(String id);
    boolean existsByIdAndUsername(String id, String username);
}
