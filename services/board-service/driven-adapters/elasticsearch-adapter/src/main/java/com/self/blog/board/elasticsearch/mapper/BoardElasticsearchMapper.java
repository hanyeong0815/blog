package com.self.blog.board.elasticsearch.mapper;

import com.self.blog.board.domain.Board;
import com.self.blog.board.elasticsearch.entity.BoardEntity;
import com.self.blog.common.support.mapper.DomainEntityMapper;
import org.mapstruct.Mapper;

@Mapper
public interface BoardElasticsearchMapper extends DomainEntityMapper<Board, BoardEntity> {
}
