package com.self.blog.board.web.service;

import com.self.blog.board.application.usecase.CategorySaveUseCase;
import com.self.blog.board.domain.Category;
import com.self.blog.board.web.dto.CategoryDto.CategorySaveRequestDto;
import com.self.blog.board.web.mapper.CategoryDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategorySaveProxyService {
    private final CategorySaveUseCase categorySaveUseCase;
    private final CategoryDtoMapper categoryDtoMapper;

    public Category saveCategory(CategorySaveRequestDto dto) {
        Category category = categoryDtoMapper.from(dto, 0L);
        return categorySaveUseCase.saveCategory(category);
    }
}
