package com.self.blog.board.application.service;

import com.self.blog.board.application.exception.CategoryErrorCode;
import com.self.blog.board.application.repository.CategoryRepository;
import com.self.blog.board.application.usecase.CategorySaveUseCase;
import com.self.blog.board.domain.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.self.blog.common.utils.exception.Preconditions.validate;

@Service
@RequiredArgsConstructor
public class CategoryService implements CategorySaveUseCase {
    private final CategoryRepository categoryRepository;

    @Override
    public Category saveCategory(Category category) {
        boolean hasCategory = categoryRepository.existsByCategory(category.category);
        validate(
                hasCategory,
                CategoryErrorCode.CATEGORY_ALREADY_USED
        );

        return categoryRepository.save(category);
    }
}
