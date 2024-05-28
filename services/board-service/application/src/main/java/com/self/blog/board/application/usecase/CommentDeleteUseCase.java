package com.self.blog.board.application.usecase;

import com.self.blog.board.domain.Comment;

public interface CommentDeleteUseCase {
    Comment deleteComment(String commentId);
}
