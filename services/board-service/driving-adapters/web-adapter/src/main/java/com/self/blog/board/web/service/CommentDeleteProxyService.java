package com.self.blog.board.web.service;

import com.self.blog.board.application.usecase.CommentDeleteUseCase;
import com.self.blog.board.domain.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentDeleteProxyService {
    private final CommentDeleteUseCase commentDeleteUseCase;

    public Comment commentDelete(String commentId) {
        return commentDeleteUseCase.deleteComment(commentId);
    }
}
