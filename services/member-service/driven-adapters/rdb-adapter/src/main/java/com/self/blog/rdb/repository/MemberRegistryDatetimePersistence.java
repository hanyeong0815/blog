package com.self.blog.rdb.repository;

import com.self.blog.application.repository.AccountRegistryDateTimeRepository;
import com.self.blog.domain.AccountRegistryDatetime;
import com.self.blog.rdb.entity.AccountRegistryDatetimeEntity;
import com.self.blog.rdb.mapper.AccountRegistryDatetimeEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class AccountRegistryDatetimePersistence implements AccountRegistryDateTimeRepository {
    private final AccountRegistryDatetimeJpaRepository repository;
    private final AccountRegistryDatetimeEntityMapper mapper;

    @Override
    public AccountRegistryDatetime save(AccountRegistryDatetime accountRegistryDatetime) {
        AccountRegistryDatetimeEntity accountRegistryDatetimeEntity = mapper.toEntity(accountRegistryDatetime);
        return mapper.toDomain(repository.save(accountRegistryDatetimeEntity));
    }

    @Override
    public Optional<AccountRegistryDatetime> findById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<AccountRegistryDatetime> findByMemberId(UUID memberId) {
        return repository.findByMemberId(memberId).map(mapper::toDomain);
    }
}
