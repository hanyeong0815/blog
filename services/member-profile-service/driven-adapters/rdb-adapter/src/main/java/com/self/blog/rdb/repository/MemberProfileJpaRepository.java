package com.self.blog.rdb.repository;

import com.self.blog.rdb.entity.MemberProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MemberProfileJpaRepository extends JpaRepository<MemberProfileEntity, Long> {
    Optional<MemberProfileEntity> findByMemberId(UUID memberId);
    Optional<MemberProfileEntity> findByUsername(String username);
}
