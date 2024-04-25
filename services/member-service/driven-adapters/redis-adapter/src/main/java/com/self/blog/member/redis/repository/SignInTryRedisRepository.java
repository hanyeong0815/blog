package com.self.blog.member.redis.repository;

import com.self.blog.member.redis.entity.SignInTryEntity;
import org.springframework.data.repository.CrudRepository;

public interface SignInTryRedisRepository extends CrudRepository<SignInTryEntity, String> {
}
