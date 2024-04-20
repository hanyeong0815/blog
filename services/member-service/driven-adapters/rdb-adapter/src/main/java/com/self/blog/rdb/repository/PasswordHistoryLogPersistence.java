package com.self.blog.rdb.repository;

import com.self.blog.application.repository.PasswordHistoryLogRepository;
import com.self.blog.domain.PasswordHistoryLog;
import com.self.blog.rdb.entity.PasswordHistoryLogEntity;
import com.self.blog.rdb.mapper.PasswordHistoryLogEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class PasswordHistoryLogPersistence implements PasswordHistoryLogRepository {
    private final PasswordHistoryLogJpaRepository repository;
    private final PasswordHistoryLogEntityMapper mapper;

    @Override
    public PasswordHistoryLog save(PasswordHistoryLog passwordHistoryLog) {
        PasswordHistoryLogEntity passwordHistoryLogEntity = mapper.toEntity(passwordHistoryLog);
        return mapper.toDomain(
                repository.save(passwordHistoryLogEntity)
        );
    }

    @Override
    public Optional<PasswordHistoryLog> findById(UUID id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<PasswordHistoryLog> findByUsername(String username) {
        return repository.findByUsername(username).map(mapper::toDomain);
    }
}
