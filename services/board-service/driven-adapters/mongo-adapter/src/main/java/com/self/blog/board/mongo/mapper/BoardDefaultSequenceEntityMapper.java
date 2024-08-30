package com.self.blog.board.mongo.mapper;

import com.self.blog.board.domain.BoardDefaultSequence;
import com.self.blog.board.mongo.entity.BoardDefaultSequenceEntity;
import com.self.blog.common.support.mapper.DomainEntityMapper;
import org.mapstruct.Mapper;

@Mapper
public interface BoardDefaultSequenceEntityMapper extends DomainEntityMapper<BoardDefaultSequence, BoardDefaultSequenceEntity> {
}
