package com.self.blog.member.redis.repository;

import com.self.blog.member.application.repository.SignInTryRepository;
import com.self.blog.common.utils.time.ServerTime;
import com.self.blog.member.domain.SignInTry;
import com.self.blog.member.redis.entity.SignInTryEntity;
import com.self.blog.member.redis.mapper.SignInTryEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SignInTryPersistence implements SignInTryRepository {
    private final SignInTryRedisRepository repository;
    private final SignInTryEntityMapper mapper;

    private final ServerTime serverTime;

    @Override
    public SignInTry save(SignInTry signInTry) {
        SignInTryEntity signInTryEntity = mapper.toEntity(signInTry);

        return mapper.toDomain(
                repository.save(signInTryEntity)
        );
    }

    @Override
    public Optional<SignInTry> findById(String username) {
        return repository.findById(username).map(mapper::toDomain);
    }

    @Override
    public SignInTry countUpSignTry(String username) {
        SignInTryEntity signInTryEntity = repository.findById(username)
                .orElseGet(() -> SignInTryEntity.builder()
                        .username(username)
                        .tryCount(0)
                        .firstTryTime(serverTime.nowInstant())
                        .ttl(86_400_000L)
                        .build());

        signInTryEntity.tryCount += 1;

        return mapper.toDomain(
                repository.save(signInTryEntity)
        );
    }

    @Override
    public void deleteById(String username) {
        repository.deleteById(username);
    }
}
