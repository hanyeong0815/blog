package com.self.blog.rdb.repository;

import com.self.blog.application.repository.SignLogRepository;
import com.self.blog.domain.SignLog;
import com.self.blog.rdb.entity.SignLogEntity;
import com.self.blog.rdb.mapper.SignLogEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SignLogPersistence implements SignLogRepository {
    private final SignLogJpaRepository repository;
    private final SignLogEntityMapper mapper;

    @Override
    public SignLog save(SignLog signLog) {
        SignLogEntity signLogEntity = mapper.toEntity(signLog);
        return mapper.toDomain(
                repository.save(
                        signLogEntity
                )
        );
    }

    @Override
    public Optional<SignLog> findById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<SignLog> findByUsername(String username) {
        return repository.findByUsername(username).map(mapper::toDomain);
    }
}
