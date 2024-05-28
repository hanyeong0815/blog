package com.self.blog.profile.rdb.repository;

import com.self.blog.profile.rdb.entity.MemberProfileEntity;
import com.self.blog.profile.rdb.projection.MemberProfileProjections.MemberProfileDetailViewProjection;
import com.self.blog.profile.rdb.projection.MemberProfileProjections.MemberProfileMemberIdProjection;
import com.self.blog.profile.rdb.projection.MemberProfileProjections.MemberProfileNicknameProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MemberProfileJpaRepository extends JpaRepository<MemberProfileEntity, Long> {
    Optional<MemberProfileEntity> findByMemberId(UUID memberId);
    Optional<MemberProfileEntity> findByUsername(String username);
    Optional<MemberProfileNicknameProjection> findNicknameByUsername(String username);
    Optional<MemberProfileDetailViewProjection> findDetailViewByUsername(String username);
    Optional<MemberProfileMemberIdProjection> findMemberIdByUsername(String username);
}
