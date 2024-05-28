package com.self.blog.board.mongo.mapper;

import com.self.blog.board.domain.BoardView;
import com.self.blog.board.mongo.entity.BoardViewEntity;
import com.self.blog.board.mongo.support.OneToOneEntityMapper;
import org.mapstruct.Mapper;

@Mapper
public interface BoardViewEntityMapper extends OneToOneEntityMapper<BoardView, BoardViewEntity> {
}
