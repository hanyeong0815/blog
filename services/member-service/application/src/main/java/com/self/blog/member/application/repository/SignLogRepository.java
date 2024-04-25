package com.self.blog.member.application.repository;

import com.self.blog.member.domain.SignLog;

import java.util.Optional;

public interface SignLogRepository {
    SignLog save(SignLog signLog);
    Optional<SignLog> findById(Long id);
    Optional<SignLog> findByUsername(String username);
}
