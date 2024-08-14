package com.self.blog.board.mongo.mapper;

import com.self.blog.board.domain.Board;
import com.self.blog.board.domain.Comment;
import com.self.blog.board.mongo.projection.BoardProjection.BoardFindForUpdateProjection;
import com.self.blog.board.mongo.projection.BoardProjection.BoardListViewProjection;
import com.self.blog.board.mongo.entity.BoardEntity;
import com.self.blog.board.mongo.entity.CommentEntity;
import com.self.blog.board.mongo.support.OneToOneEntityMapper;
import com.self.blog.board.readmodels.BoardReadModels.BoardFindForUpdateReadModel;
import com.self.blog.board.readmodels.BoardReadModels.BoardListViewReadModel;
import org.mapstruct.Mapper;

@Mapper
public interface BoardEntityMapper extends OneToOneEntityMapper<Board, BoardEntity> {
    Comment replyToDomain(CommentEntity commentEntity);
    BoardListViewReadModel projectionToReadModel(BoardListViewProjection projection);
    BoardFindForUpdateReadModel projectionToReadModel(BoardFindForUpdateProjection projection);
}
