package com.self.blog.member.rdb.repository;

import com.self.blog.member.rdb.entity.PasswordLastUpdateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;

public interface PasswordLastUpdateJpaRepository extends JpaRepository<PasswordLastUpdateEntity, Long> {
    Optional<PasswordLastUpdateEntity> findByUsername(String username);

    @Transactional
    @Modifying
    @Query(
            "update PasswordLastUpdateEntity plue set plue.createdAt = ?2 where plue.username = ?1"
    )
    int updateCreatedAt(String username, Instant createdAt);
}
