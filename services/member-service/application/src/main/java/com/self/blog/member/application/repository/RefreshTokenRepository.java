package com.self.blog.member.application.repository;

import com.self.blog.member.domain.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository {
    RefreshToken save(RefreshToken refreshToken);
    Optional<RefreshToken> findById(String id);
    Optional<RefreshToken> findBySubject(String subject);
    void deleteById(String refreshToken);
}
