package com.self.blog.rdb.repository;

import com.self.blog.domain.AccountRegistryDatetime;
import com.self.blog.rdb.entity.AccountRegistryDatetimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccountRegistryDatetimeJpaRepository extends JpaRepository<AccountRegistryDatetimeEntity, Long> {
    Optional<AccountRegistryDatetimeEntity> findByMemberId(UUID memberId);
}
