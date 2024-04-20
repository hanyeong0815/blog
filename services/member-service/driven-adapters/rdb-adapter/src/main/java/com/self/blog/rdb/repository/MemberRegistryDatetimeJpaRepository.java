package com.self.blog.rdb.repository;

import com.self.blog.rdb.entity.MemberRegistryDatetimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MemberRegistryDatetimeJpaRepository extends JpaRepository<MemberRegistryDatetimeEntity, Long> {
    Optional<MemberRegistryDatetimeEntity> findByMemberId(UUID memberId);
}
