package com.self.blog.rdb.repository;

import com.self.blog.application.repository.PasswordLastUpdateRepository;
import com.self.blog.domain.PasswordLastUpdate;
import com.self.blog.rdb.entity.PasswordLastUpdateEntity;
import com.self.blog.rdb.mapper.PasswordLastUpdateEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PasswordLastUpdatePersistence implements PasswordLastUpdateRepository {
    private final PasswordLastUpdateJpaRepository repository;
    private final PasswordLastUpdateEntityMapper mapper;

    @Override
    public PasswordLastUpdate save(PasswordLastUpdate passwordLastUpdate) {
        PasswordLastUpdateEntity passwordLastUpdateEntity = mapper.toEntity(passwordLastUpdate);
        return mapper.toDomain(repository.save(passwordLastUpdateEntity));
    }

    @Override
    public Optional<PasswordLastUpdate> findById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<PasswordLastUpdate> findByUsername(String username) {
        return repository.findByUsername(username).map(mapper::toDomain);
    }
}
