package com.self.blog.board.mongo.mapper;

import com.self.blog.board.domain.Board;
import com.self.blog.board.mongo.entity.BoardEntity;
import com.self.blog.board.mongo.support.OneToOneEntityMapper;
import org.mapstruct.Mapper;

@Mapper
public interface BoardEntityMapper extends OneToOneEntityMapper<Board, BoardEntity> {
}
