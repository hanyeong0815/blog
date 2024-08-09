package com.self.blog.board.application.usecase;

import com.self.blog.board.domain.Category;

import java.util.List;

public interface CategoryFindAllUseCase {
    List<Category> findAll();
}
