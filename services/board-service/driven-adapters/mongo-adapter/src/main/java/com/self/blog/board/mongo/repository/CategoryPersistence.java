package com.self.blog.board.mongo.repository;

import com.self.blog.board.application.repository.CategoryRepository;
import com.self.blog.board.domain.Domain;
import com.self.blog.board.mongo.entity.DomainEntity;
import com.self.blog.board.mongo.mapper.DomainEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CategoryPersistence implements CategoryRepository {
    private final DomainMongoRepository repository;
    private final DomainEntityMapper mapper;

    @Override
    public Domain save(Domain domain) {
        DomainEntity domainEntity = mapper.toEntity(domain);
        return mapper.toDomain(
                repository.save(domainEntity)
        );
    }

    @Override
    public Optional<Domain> findById(String id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Domain> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public boolean existsByCategory(String category) {
        return repository.existsByDomain(category);
    }

    @Override
    public Long countUpAndGetSequence(String domain) {
        DomainEntity findDomainEntity = repository.findByDomain(domain).orElse(
                DomainEntity.builder()
                        .domain(domain)
                        .sequence(0L)
                        .build()
        );

        findDomainEntity.setSequence(findDomainEntity.getSequence() + 1);

        return repository.save(findDomainEntity).getSequence();
    }
}
