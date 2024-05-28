package com.self.blog.member.rdb.repository;

import com.self.blog.member.domain.type.MemberStatus;
import com.self.blog.member.rdb.entity.MemberEntity;
import com.self.blog.member.rdb.projection.MemberProjections.MemberIdProjection;
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

    @Transactional
    @Modifying
    @Query(
            "update MemberEntity me set me.password = ?2 where me.username = ?1"
    )
    int updatePassword(String username, String password);
}
