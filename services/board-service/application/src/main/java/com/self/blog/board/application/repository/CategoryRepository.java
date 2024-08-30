package com.self.blog.board.application.repository;

import com.self.blog.board.domain.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    Category save(Category category);
    Optional<Category> findById(String id);
    List<Category> findAll();
    boolean existsByCategory(String category);
    Long countUpAndGetSequence(String category);
}
