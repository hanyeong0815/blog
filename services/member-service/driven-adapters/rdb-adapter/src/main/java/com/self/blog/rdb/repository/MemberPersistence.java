package com.self.blog.rdb.repository;

import com.self.blog.application.repository.MemberRepository;
import com.self.blog.domain.Member;
import com.self.blog.domain.type.MemberStatus;
import com.self.blog.rdb.entity.MemberEntity;
import com.self.blog.rdb.mapper.MemberEntityMapper;
import com.self.blog.readmodel.MemberReadModels.MemberIdReadModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberPersistence implements MemberRepository {
    private final MemberEntityMapper mapper;
    private final MemberJpaRepository repository;

    @Override
    public Member save(Member member) {
        MemberEntity memberEntity = mapper.toEntity(member);
        return mapper.toDomain(
                repository.save(memberEntity)
        );
    }

    @Override
    public Optional<Member> findByUsername(String username) {
        return repository.findByUsername(username)
                .map(mapper::toDomain);
    }

    @Override
    public boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }

    @Override
    public Optional<MemberIdReadModel> findIdByUsername(String name) {
        return repository.findIdByUsername(name).map(mapper::readModelFromProjection);
    }

    @Override
    public boolean updateMemberStatus(String username, MemberStatus memberStatus) {
        return repository.updateStatus(username, memberStatus) >= 1;
    }
}
