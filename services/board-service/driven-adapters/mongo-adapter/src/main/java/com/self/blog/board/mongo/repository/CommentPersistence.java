package com.self.blog.board.mongo.repository;

import com.self.blog.board.application.repository.CommentRepository;
import com.self.blog.board.domain.Comment;
import com.self.blog.board.mongo.entity.CommentEntity;
import com.self.blog.board.mongo.mapper.ReplyEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CommentPersistence implements CommentRepository {
    private final ReplyMongoRepository repository;
    private final ReplyEntityMapper mapper;

    @Override
    public Comment save(Comment comment) {
        CommentEntity commentEntity = mapper.toEntity(comment);
        return mapper.toDomain(
                repository.save(commentEntity)
        );
    }

    @Override
    public Optional<Comment> findById(String replyId) {
        return repository.findById(replyId)
                .map(mapper::toDomain);
    }
}
