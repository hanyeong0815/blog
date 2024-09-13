package com.self.blog.profile.rdb.repository;

import com.self.blog.profile.application.repository.BlogDomainRepository;
import com.self.blog.profile.domain.BlogDomain;
import com.self.blog.profile.domain.type.BlogDomainStatus;
import com.self.blog.profile.rdb.mapper.BlogDomainEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BlogDomainPersistence implements BlogDomainRepository {
    private final BlogDomainEntityRepository repository;
    private final BlogDomainEntityMapper mapper;

    @Override
    public BlogDomain save(BlogDomain blogDomain) {
        return mapper.toDomain(
                repository.save(
                        mapper.toEntity(blogDomain)
                )
        );
    }

    @Override
    public Optional<BlogDomain> findByProfileId(Long profileId) {
        return repository.findByProfileIdAndStatus(profileId, BlogDomainStatus.ACTIVE).map(mapper::toDomain);
    }

    @Override
    public boolean existsByDomain(String domain) {
        return repository.existsByDomain(domain);
    }

    @Override
    public boolean existsByProfileId(Long profileId) {
        return repository.existsByProfileId(profileId);
    }
}
