package com.self.blog.member.rdb.repository;

import com.self.blog.member.rdb.entity.SignLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SignLogJpaRepository extends JpaRepository<SignLogEntity, Long> {
    Optional<SignLogEntity> findByUsername(String username);
}
