package com.self.blog.board.mongo.repository;

import com.self.blog.board.mongo.entity.CategoryEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CategoryMongoRepository extends MongoRepository<CategoryEntity, String> {
    Optional<CategoryEntity> findByCategory(String category);
    boolean existsByCategory(String category);
}
