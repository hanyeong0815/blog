package com.self.blog.board.mongo.mapper;

import com.self.blog.board.domain.FavoriteBoard;
import com.self.blog.board.mongo.entity.FavoriteBoardEntity;
import com.self.blog.board.mongo.projection.FavoriteBoardProjection.FavoriteBoardListProjection;
import com.self.blog.board.readmodels.FavoriteBoardReadModel.FavoriteBoardListReadModel;
import com.self.blog.common.support.mapper.DomainEntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface FavoriteBoardEntityMapper extends DomainEntityMapper<FavoriteBoard, FavoriteBoardEntity> {
    @Mapping(target = "favoriteBoardList", source = "content")
    FavoriteBoardListReadModel from(FavoriteBoardListProjection favoriteBoardListProjection);
}
