package com.self.blog.board.application.repository;

import com.self.blog.board.domain.ViewCountValidation;

public interface ViewCountValidationRepository {
    ViewCountValidation save(ViewCountValidation viewCountValidation);
    boolean existsByIdAndUsername(String boardId, String username);
}
