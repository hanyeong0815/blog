package com.self.blog.board.mongo.mapper;

import com.self.blog.board.domain.Comment;
import com.self.blog.board.mongo.entity.CommentEntity;
import com.self.blog.board.mongo.support.OneToOneEntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface CommentEntityMapper extends OneToOneEntityMapper<Comment, CommentEntity> {
    @Mapping(target = "isDeleted", source = "comment.deleted")
    CommentEntity toEntity(Comment comment);

    @Mapping(target = "isDeleted", source = "commentEntity.deleted")
    Comment toDomain(CommentEntity commentEntity);
}
