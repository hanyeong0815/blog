package com.self.blog.board.mongo.mapper;

import com.self.blog.board.domain.Domain;
import com.self.blog.board.mongo.entity.DomainEntity;
import com.self.blog.board.mongo.support.OneToOneEntityMapper;
import org.mapstruct.Mapper;

@Mapper
public interface CategoryEntityMapper extends OneToOneEntityMapper<Domain, DomainEntity> {
}
