package com.self.blog.board.mongo.repository;

import com.self.blog.board.application.repository.CategoryRepository;
import com.self.blog.board.domain.Category;
import com.self.blog.board.mongo.entity.CategoryEntity;
import com.self.blog.board.mongo.mapper.CategoryEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CategoryPersistence implements CategoryRepository {
    private final CategoryMongoRepository repository;
    private final CategoryEntityMapper mapper;

    @Override
    public Category save(Category category) {
        CategoryEntity categoryEntity = mapper.toEntity(category);
        return mapper.toDomain(
                repository.save(categoryEntity)
        );
    }

    @Override
    public Optional<Category> findById(String id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Category> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public boolean existsByCategory(String category) {
        return repository.existsByCategory(category);
    }
}
