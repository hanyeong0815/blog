package com.self.blog.profile.redis.repository;

import com.self.blog.profile.application.repository.ReservationNicknameRepository;
import com.self.blog.profile.domain.ReservationNickname;
import com.self.blog.profile.redis.mapper.ReservationNicknameEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ReservationNicknamePersistence implements ReservationNicknameRepository {
    private final ReservationNicknameRedisRepository repository;
    private final ReservationNicknameEntityMapper mapper;

    @Override
    public ReservationNickname save(ReservationNickname reservationNickname) {
        return mapper.toDomain(
                repository.save(
                        mapper.toEntity(reservationNickname)
                )
        );
    }

    @Override
    public boolean existsById(String nickname) {
        return repository.existsById(nickname);
    }
}
