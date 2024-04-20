package com.self.blog.rdb.repository;

import com.self.blog.application.repository.MemberRegistryDateTimeRepository;
import com.self.blog.domain.MemberRegistryDatetime;
import com.self.blog.rdb.entity.MemberRegistryDatetimeEntity;
import com.self.blog.rdb.mapper.MemberRegistryDatetimeEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class MemberRegistryDatetimePersistence implements MemberRegistryDateTimeRepository {
    private final MemberRegistryDatetimeJpaRepository repository;
    private final MemberRegistryDatetimeEntityMapper mapper;

    @Override
    public MemberRegistryDatetime save(MemberRegistryDatetime memberRegistryDatetime) {
        MemberRegistryDatetimeEntity memberRegistryDatetimeEntity = mapper.toEntity(memberRegistryDatetime);
        return mapper.toDomain(repository.save(memberRegistryDatetimeEntity));
    }

    @Override
    public Optional<MemberRegistryDatetime> findById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<MemberRegistryDatetime> findByMemberId(UUID memberId) {
        return repository.findByMemberId(memberId).map(mapper::toDomain);
    }
}
