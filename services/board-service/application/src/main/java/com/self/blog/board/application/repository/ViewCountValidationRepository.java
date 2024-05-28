package com.self.blog.board.application.repository;

import com.self.blog.board.domain.ViewCountValidation;

public interface ViewCountValidationRepository {
    ViewCountValidation save(ViewCountValidation viewCountValidation);
    boolean existsViewCountValidationByIdAndUsernameAndViewIp(String boardId, String username, String viewIp);
}
