package com.self.blog.member.application.repository;

import com.self.blog.member.domain.PasswordHistoryLog;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PasswordHistoryLogRepository {
    PasswordHistoryLog save(PasswordHistoryLog passwordHistoryLog);
    Optional<PasswordHistoryLog> findById(UUID id);
    List<String> findByUsername(String username);
}
