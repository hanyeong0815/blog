package com.self.blog.member.rdb.repository;

import com.self.blog.member.application.repository.MemberStaticSaltRepository;
import com.self.blog.member.domain.MemberStaticSalt;
import com.self.blog.member.rdb.entity.MemberStaticSaltEntity;
import com.self.blog.member.rdb.mapper.MemberStaticSaltEntityMapper;
import com.self.blog.member.rdb.repository.MemberStaticSaltJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class MemberStaticSaltPersistence implements MemberStaticSaltRepository {
    private final MemberStaticSaltJpaRepository repository;
    private final MemberStaticSaltEntityMapper mapper;

    @Override
    public MemberStaticSalt save(MemberStaticSalt memberStaticSalt) {
        MemberStaticSaltEntity memberStaticSaltEntity = mapper.toEntity(memberStaticSalt);
        return mapper.toDomain(
                repository.save(memberStaticSaltEntity)
        );
    }

    @Override
    public Optional<MemberStaticSalt> findById(UUID id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<MemberStaticSalt> findByUsername(String username) {
        return repository.findByUsername(username).map(mapper::toDomain);
    }

    @Override
    public Optional<MemberStaticSalt> findTopByUsernameOrderByCreatedAt(String username) {
        return repository.findTopByUsernameOrderByCreatedAt(username).map(mapper::toDomain);
    }
}
