package com.self.blog.member.redis.repository;

import com.self.blog.member.application.repository.InvitationCodeRepository;
import com.self.blog.member.domain.InvitationCode;
import com.self.blog.member.redis.entity.InvitationCodeEntity;
import com.self.blog.member.redis.mapper.InvitationCodeEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class InvitationCodePersistence implements InvitationCodeRepository {
    private final InvitationCodeRedisRepository repository;
    private final InvitationCodeEntityMapper mapper;

    @Override
    public InvitationCode save(InvitationCode invitationCode) {
        InvitationCodeEntity entity = mapper.toEntity(invitationCode);
        return mapper.toDomain(repository.save(entity));
    }

    @Override
    public Optional<InvitationCode> findById(String invitationCode) {
        return repository.findById(invitationCode)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<InvitationCode> findByEmail(String email) {
        return repository.findByEmail(email).map(mapper::toDomain);
    }

    @Override
    public void deleteById(String invitationCode) {
        repository.deleteById(invitationCode);
    }
}
