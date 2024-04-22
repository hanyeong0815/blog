package com.self.blog.rdb.repository;

import com.self.blog.application.repository.MemberProfileRepository;
import com.self.blog.domain.MemberProfile;
import com.self.blog.rdb.entity.MemberProfileEntity;
import com.self.blog.rdb.mapper.MemberProfileEntityMapper;
import com.self.blog.read_model.MemberProfileReadModels.MemberProfileDetailView;
import com.self.blog.read_model.MemberProfileReadModels.MemberProfileNickname;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class MemberProfilePersistence implements MemberProfileRepository {
    private final MemberProfileJpaRepository repository;
    private final MemberProfileEntityMapper mapper;

    @Override
    public MemberProfile save(MemberProfile memberProfile) {
        MemberProfileEntity memberProfileEntity = mapper.toEntity(memberProfile);
        return mapper.toDomain(
                repository.save(memberProfileEntity)
        );
    }

    @Override
    public Optional<MemberProfile> findByUsername(String username) {
        return repository.findByUsername(username).map(mapper::toDomain);
    }

    @Override
    public Optional<MemberProfile> findByMemberId(UUID memberId) {
        return repository.findByMemberId(memberId).map(mapper::toDomain);
    }

    @Override
    public Optional<MemberProfileNickname> findNicknameByUsername(String username) {
        return repository.findNicknameByUsername(username).map(mapper::from);
    }

    @Override
    public Optional<MemberProfileDetailView> findDetailViewByUsername(String username) {
        return repository.findDetailViewByUsername(username).map(mapper::from);
    }
}
