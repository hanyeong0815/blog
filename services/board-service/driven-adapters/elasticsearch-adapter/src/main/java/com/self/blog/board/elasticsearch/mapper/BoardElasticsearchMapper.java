package com.self.blog.board.elasticsearch.mapper;

import com.self.blog.board.application.usecase.data.BoardListViewDto.BoardRecommendListView;
import com.self.blog.board.domain.Board;
import com.self.blog.board.domain.BoardElasticsearch;
import com.self.blog.board.elasticsearch.entity.BoardEntity;
import com.self.blog.common.support.mapper.DomainEntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface BoardElasticsearchMapper extends DomainEntityMapper<Board, BoardEntity> {
    @Mapping(target = "boardId", source = "boardEntity.id")
    BoardRecommendListView from(BoardEntity boardEntity);
    BoardEntity toEntity(BoardElasticsearch boardElasticsearch);
}
