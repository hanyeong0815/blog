package com.self.blog.redis.mapper;

import com.self.blog.common.support.mapper.DomainEntityMapper;
import com.self.blog.domain.SignInTry;
import com.self.blog.redis.entity.SignInTryEntity;
import org.mapstruct.Mapper;

@Mapper
public interface SignInTryEntityMapper extends DomainEntityMapper<SignInTry, SignInTryEntity> {
}
