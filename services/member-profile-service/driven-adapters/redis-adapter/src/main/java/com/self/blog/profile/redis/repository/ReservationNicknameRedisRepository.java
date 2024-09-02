package com.self.blog.profile.redis.repository;

import com.self.blog.profile.redis.entity.ReservationNicknameEntity;
import org.springframework.data.repository.CrudRepository;

public interface ReservationNicknameRedisRepository extends CrudRepository<ReservationNicknameEntity, String> {
}
