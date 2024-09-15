package com.self.blog.board.application.service;

import com.self.blog.board.application.exception.CategoryErrorCode;
import com.self.blog.board.application.repository.DomainRepository;
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
    private final DomainRepository domainRepository;

    @Override
    public Domain saveCategory(Domain domain) {
        boolean hasCategory = domainRepository.existsByCategory(domain.getDomain());
        validate(
                !hasCategory,
                CategoryErrorCode.CATEGORY_ALREADY_USED
        );

        return domainRepository.save(domain);
    }

    @Override
    public List<Domain> findAll() {
        return domainRepository.findAll();
    }
}
