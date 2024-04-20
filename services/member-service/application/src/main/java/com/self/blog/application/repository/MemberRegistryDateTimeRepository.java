package com.self.blog.application.repository;

import com.self.blog.domain.AccountRegistryDatetime;

import java.util.Optional;
import java.util.UUID;

public interface AccountRegistryDateTimeRepository {
    AccountRegistryDatetime save(AccountRegistryDatetime accountRegistryDatetime);
    Optional<AccountRegistryDatetime> findById(Long id);
    Optional<AccountRegistryDatetime> findByMemberId(UUID memberId);
}
