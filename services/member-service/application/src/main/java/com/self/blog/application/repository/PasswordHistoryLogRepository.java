package com.self.blog.application.repository;

import com.self.blog.domain.PasswordHistoryLog;

import java.util.Optional;
import java.util.UUID;

public interface PasswordHistoryLogRepository {
    PasswordHistoryLog save(PasswordHistoryLog passwordHistoryLog);
    Optional<PasswordHistoryLog> findById(UUID id);
    Optional<PasswordHistoryLog> findByUsername(String username);
}
