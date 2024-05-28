package com.self.blog.member.application.repository;

import com.self.blog.member.domain.PasswordLastUpdate;

import java.time.Instant;
import java.util.Optional;

public interface PasswordLastUpdateRepository {
    PasswordLastUpdate save(PasswordLastUpdate passwordLastUpdate);
    Optional<PasswordLastUpdate> findById(Long id);
    Optional<PasswordLastUpdate> findByUsername(String username);
    boolean updateCreatedAt(String username, Instant createdAt);
}
