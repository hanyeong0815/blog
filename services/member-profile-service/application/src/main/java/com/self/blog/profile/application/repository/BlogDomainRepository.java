package com.self.blog.profile.application.repository;

import com.self.blog.profile.domain.BlogDomain;

import java.util.Optional;

public interface BlogDomainRepository {
    BlogDomain save(BlogDomain blogDomain);
    Optional<BlogDomain> findByProfileId(Long profileId);
    boolean existsByDomain(String domain);
    boolean existsByProfileId(Long profileId);
}
