package com.self.blog.member.redis.mapper;

import com.self.blog.common.support.mapper.DomainEntityMapper;
import com.self.blog.member.domain.RefreshToken;
import com.self.blog.member.redis.entity.RefreshTokenEntity;
import org.mapstruct.Mapper;

@Mapper
public interface RefreshTokenEntityMapper extends DomainEntityMapper<RefreshToken, RefreshTokenEntity> {
}
