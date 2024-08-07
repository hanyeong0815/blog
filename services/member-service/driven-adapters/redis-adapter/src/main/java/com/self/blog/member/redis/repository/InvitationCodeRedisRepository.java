package com.self.blog.member.redis.repository;

import com.self.blog.member.redis.entity.InvitationCodeEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface InvitationCodeRedisRepository extends CrudRepository<InvitationCodeEntity, String> {
    Optional<InvitationCodeEntity> findByEmail(String email);
}
