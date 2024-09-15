package com.self.blog.board.application.service;

import com.self.blog.board.application.exception.CategoryErrorCode;
import com.self.blog.board.application.repository.CategoryRepository;
import com.self.blog.board.application.usecase.CategoryFindAllUseCase;
import com.self.blog.board.application.usecase.CategorySaveUseCase;
import com.self.blog.board.domain.Domain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.self.blog.common.utils.exception.Preconditions.validate;

@Service
@RequiredArgsConstructor
public class CategoryService implements
        CategorySaveUseCase,
        CategoryFindAllUseCase
{
    private final CategoryRepository categoryRepository;

    @Override
    public Domain saveCategory(Domain domain) {
        boolean hasCategory = categoryRepository.existsByCategory(domain.getDomain());
        validate(
                !hasCategory,
                CategoryErrorCode.CATEGORY_ALREADY_USED
        );

        return categoryRepository.save(domain);
    }

    @Override
    public List<Domain> findAll() {
        return categoryRepository.findAll();
    }
}
