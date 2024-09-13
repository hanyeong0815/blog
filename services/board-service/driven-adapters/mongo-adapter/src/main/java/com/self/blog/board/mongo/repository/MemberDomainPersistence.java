package com.self.blog.board.mongo.repository;

import com.self.blog.board.application.repository.MemberDomainRepository;
import com.self.blog.board.domain.MemberDomain;
import com.self.blog.board.mongo.mapper.MemberDomainEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberDomainPersistence implements MemberDomainRepository {
    private final MemberDomainEntityRepository repository;
    private final MemberDomainEntityMapper mapper;

    @Override
    public MemberDomain save(MemberDomain memberDomain) {
        return mapper.toDomain(
                repository.save(
                        mapper.toEntity(memberDomain)
                )
        );
    }

    @Override
    public Optional<MemberDomain> findById(String id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<MemberDomain> findByDomain(String domain) {
        return repository.findByDomain(domain).map(mapper::toDomain);
    }
}
