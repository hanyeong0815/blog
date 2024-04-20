package com.self.blog.redis.repository;

import com.self.blog.redis.entity.SignInTryEntity;
import org.springframework.data.repository.CrudRepository;

public interface SignInTryRedisRepository extends CrudRepository<SignInTryEntity, String> {
}
