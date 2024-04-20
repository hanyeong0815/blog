package com.self.blog.redis.mapper;

import com.self.blog.common.support.mapper.DomainEntityMapper;
import com.self.blog.domain.RefreshToken;
import com.self.blog.redis.entity.RefreshTokenEntity;
import org.mapstruct.Mapper;

@Mapper
public interface RefreshTokenEntityMapper extends DomainEntityMapper<RefreshToken, RefreshTokenEntity> {
}
