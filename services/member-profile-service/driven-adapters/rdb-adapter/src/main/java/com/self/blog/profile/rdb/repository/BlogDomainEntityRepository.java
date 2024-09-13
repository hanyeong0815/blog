package com.self.blog.profile.rdb.repository;

import com.self.blog.profile.domain.type.BlogDomainStatus;
import com.self.blog.profile.rdb.entity.BlogDomainEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BlogDomainEntityRepository extends JpaRepository<BlogDomainEntity, Long> {
    Optional<BlogDomainEntity> findByProfileIdAndStatus(Long profileId, BlogDomainStatus status);
    boolean existsByDomain(String domain);
    boolean existsByProfileId(Long profileId);
}
