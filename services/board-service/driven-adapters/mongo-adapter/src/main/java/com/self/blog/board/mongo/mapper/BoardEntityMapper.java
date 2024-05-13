package com.self.blog.board.mongo.mapper;

import com.self.blog.board.domain.Board;
import com.self.blog.board.domain.Comment;
import com.self.blog.board.mongo.BoardProjection.BoardListViewProjection;
import com.self.blog.board.mongo.entity.BoardEntity;
import com.self.blog.board.mongo.entity.CommentEntity;
import com.self.blog.board.mongo.support.OneToOneEntityMapper;
import com.self.blog.board.readmodels.BoardReadModels.BoardListViewReadModels;
import org.mapstruct.Mapper;

@Mapper
public interface BoardEntityMapper extends OneToOneEntityMapper<Board, BoardEntity> {
    Comment replyToDomain(CommentEntity commentEntity);
    BoardListViewReadModels projectionToReadModel(BoardListViewProjection projection);
}
