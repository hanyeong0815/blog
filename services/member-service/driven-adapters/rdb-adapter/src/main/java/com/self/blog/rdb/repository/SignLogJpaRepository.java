package com.self.blog.rdb.repository;

import com.self.blog.rdb.entity.SignLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SignLogJpaRepository extends JpaRepository<SignLogEntity, Long> {
    Optional<SignLogEntity> findByUsername(String username);
}
