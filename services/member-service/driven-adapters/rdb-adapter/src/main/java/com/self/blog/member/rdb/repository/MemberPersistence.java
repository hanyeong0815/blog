package com.self.blog.member.rdb.repository;

import com.self.blog.member.application.exception.MemberErrorCode;
import com.self.blog.member.application.repository.MemberRepository;
import com.self.blog.member.domain.Member;
import com.self.blog.member.domain.type.MemberStatus;
import com.self.blog.member.rdb.entity.MemberEntity;
import com.self.blog.member.rdb.mapper.MemberEntityMapper;
import com.self.blog.member.readmodel.MemberReadModels.MemberIdReadModel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

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
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Member> findByUsername(String username) {
        return repository.findByUsername(username)
                .map(mapper::toDomain);
    }

    @Override
    public Collection<? extends GrantedAuthority> findRolesByUsername(String username) {
        MemberEntity memberEntity = repository.findByUsername(username)
                .orElseThrow(
                        MemberErrorCode.NO_SUCH_USER::defaultException
                );
        return memberEntity.getAuthorities();
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

    @Override
    public boolean updateMemberPassword(String username, String password) {
        return repository.updatePassword(username, password) >= 1;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(Member member) {
        return mapper.toEntity(member).getAuthorities();
    }
}
