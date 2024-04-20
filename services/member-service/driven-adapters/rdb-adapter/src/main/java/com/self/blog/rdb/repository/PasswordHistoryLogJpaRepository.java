package com.self.blog.rdb.repository;

import com.self.blog.rdb.entity.PasswordHistoryLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PasswordHistoryLogJpaRepository extends JpaRepository<PasswordHistoryLogEntity, UUID> {
    Optional<PasswordHistoryLogEntity> findByUsername(String username);
}
