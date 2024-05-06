package com.self.blog.board.application.repository;

import com.self.blog.board.domain.Comment;

import java.util.Optional;

public interface CommentRepository {
    Comment save(Comment comment);
    Optional<Comment> findById(String replyId);
}
