package com.self.blog.board.redis.mapper;

import com.self.blog.board.domain.ViewCountValidation;
import com.self.blog.board.redis.entity.ViewCountValidationEntity;
import com.self.blog.common.support.mapper.DomainEntityMapper;
import org.mapstruct.Mapper;

@Mapper
public interface ViewCountValidationEntityMapper extends DomainEntityMapper<ViewCountValidation, ViewCountValidationEntity> {
}
