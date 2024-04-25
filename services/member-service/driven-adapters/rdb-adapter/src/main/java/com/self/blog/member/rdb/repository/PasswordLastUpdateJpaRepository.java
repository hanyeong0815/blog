package com.self.blog.member.rdb.repository;

import com.self.blog.member.rdb.entity.PasswordLastUpdateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PasswordLastUpdateJpaRepository extends JpaRepository<PasswordLastUpdateEntity, Long> {
    Optional<PasswordLastUpdateEntity> findByUsername(String username);
}
