package com.self.blog.member.redis.mapper;

import com.self.blog.common.support.mapper.DomainEntityMapper;
import com.self.blog.member.domain.SignInTry;
import com.self.blog.member.redis.entity.SignInTryEntity;
import org.mapstruct.Mapper;

@Mapper
public interface SignInTryEntityMapper extends DomainEntityMapper<SignInTry, SignInTryEntity> {
}
