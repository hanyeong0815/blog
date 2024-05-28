package com.self.blog.board.application.usecase;

import com.self.blog.board.domain.Comment;

public interface CommentSaveUseCase {
    Comment saveComment(String boardId, Comment comment);
}
