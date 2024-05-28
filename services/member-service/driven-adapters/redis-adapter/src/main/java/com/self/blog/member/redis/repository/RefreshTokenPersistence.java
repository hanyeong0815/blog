package com.self.blog.member.redis.repository;

import com.self.blog.member.application.repository.RefreshTokenRepository;
import com.self.blog.member.domain.RefreshToken;
import com.self.blog.member.redis.mapper.RefreshTokenEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RefreshTokenPersistence implements RefreshTokenRepository {
    private final RefreshTokenRedisRepository repository;
    private final RefreshTokenEntityMapper mapper;

    @Override
    public RefreshToken save(RefreshToken refreshToken) {
        return mapper.toDomain(
                repository.save(
                        mapper.toEntity(refreshToken)
                )
        );
    }

    @Override
    public Optional<RefreshToken> findById(String id) {
        return repository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<RefreshToken> findBySubject(String subject) {
        return repository.findBySubject(subject)
                .map(mapper::toDomain);
    }
}
