package com.self.blog.rdb.repository;

import com.self.blog.domain.type.MemberStatus;
import com.self.blog.rdb.entity.MemberEntity;
import com.self.blog.rdb.projection.MemberProjections.MemberIdProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

public interface MemberJpaRepository extends JpaRepository<MemberEntity, UUID> {
    Optional<MemberEntity> findByUsername(String username);
    boolean existsByUsername(String username);
    Optional<MemberIdProjection> findIdByUsername(String username);

    @Transactional
    @Modifying
    @Query(
            "update MemberEntity me set me.status = ?2 where me.username = ?1"
    )
    int updateStatus(String username, MemberStatus memberStatus);
}
