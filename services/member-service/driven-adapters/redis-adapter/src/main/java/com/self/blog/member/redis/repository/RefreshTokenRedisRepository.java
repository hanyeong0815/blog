package com.self.blog.member.redis.repository;

import com.self.blog.member.redis.entity.RefreshTokenEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RefreshTokenRedisRepository extends CrudRepository<RefreshTokenEntity, String> {
    Optional<RefreshTokenEntity> findBySubject(String subject);
}
