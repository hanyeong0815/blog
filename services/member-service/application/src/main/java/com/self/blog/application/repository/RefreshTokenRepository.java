package com.self.blog.application.repository;

import com.self.blog.domain.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository {
    RefreshToken save(RefreshToken refreshToken);
    Optional<RefreshToken> findById(String id);
    Optional<RefreshToken> findBySubject(String subject);
}
