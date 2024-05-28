package com.self.blog.member.rdb.repository;

import com.self.blog.member.rdb.entity.PasswordHistoryLogEntity;
import com.self.blog.member.rdb.projection.PasswordHistoryLogProjection.PasswordHistoryLogsProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PasswordHistoryLogJpaRepository extends JpaRepository<PasswordHistoryLogEntity, UUID> {
    List<PasswordHistoryLogsProjection> findByUsername(String username);
}
