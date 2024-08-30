package com.self.blog.board.web.service;

import com.self.blog.board.application.usecase.CategoryFindAllUseCase;
import com.self.blog.board.domain.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryFindAllProxyService {
    private final CategoryFindAllUseCase categoryFindAllUseCase;

    public List<Category> findAll() {
        return categoryFindAllUseCase.findAll();
    }
}
