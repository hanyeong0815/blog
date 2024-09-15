package com.self.blog.board.application.repository;

import com.self.blog.board.domain.Domain;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    Domain save(Domain domain);
    Optional<Domain> findById(String id);
    List<Domain> findAll();
    boolean existsByCategory(String category);
    Long countUpAndGetSequence(String domain);
}
