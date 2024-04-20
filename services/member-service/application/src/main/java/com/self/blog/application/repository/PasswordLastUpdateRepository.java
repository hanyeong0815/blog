package com.self.blog.application.repository;

import com.self.blog.domain.PasswordLastUpdate;

import java.util.Optional;

public interface PasswordLastUpdateRepository {
    PasswordLastUpdate save(PasswordLastUpdate passwordLastUpdate);
    Optional<PasswordLastUpdate> findById(Long id);
    Optional<PasswordLastUpdate> findByUsername(String username);
}
