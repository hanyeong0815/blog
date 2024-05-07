package com.self.blog.board.application.repository;

import com.self.blog.board.domain.Category;

import java.util.Optional;

public interface CategoryRepository {
    Category save(Category category);
    Optional<Category> findById(String id);
    Optional<Category> findByCategory(String category);
    boolean existsByCategory(String category);
}
