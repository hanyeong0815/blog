package com.self.blog.board.application.usecase;

import com.self.blog.board.domain.Comment;

public interface CommentUpdateUseCase {
    boolean commentUpdate(Comment comment);
}
