package com.self.blog.member.rdb.repository;

import com.self.blog.member.rdb.entity.MemberRegistryDatetimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MemberRegistryDatetimeJpaRepository extends JpaRepository<MemberRegistryDatetimeEntity, Long> {
    Optional<MemberRegistryDatetimeEntity> findByMemberId(UUID memberId);
}
