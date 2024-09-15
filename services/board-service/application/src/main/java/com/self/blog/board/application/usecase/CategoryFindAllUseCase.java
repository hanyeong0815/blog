package com.self.blog.board.application.usecase;

import com.self.blog.board.domain.Domain;

import java.util.List;

public interface CategoryFindAllUseCase {
    List<Domain> findAll();
}
