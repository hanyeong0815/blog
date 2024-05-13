package com.self.blog.board.mongo.mapper;

import com.self.blog.board.domain.Comment;
import com.self.blog.board.mongo.entity.CommentEntity;
import com.self.blog.board.mongo.support.OneToOneEntityMapper;
import org.mapstruct.Mapper;

@Mapper
public interface ReplyEntityMapper extends OneToOneEntityMapper<Comment, CommentEntity> {
}
