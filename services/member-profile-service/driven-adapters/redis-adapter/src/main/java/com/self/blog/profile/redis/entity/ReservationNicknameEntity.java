package com.self.blog.profile.redis.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.util.concurrent.TimeUnit;

@RedisHash("reservation-nickname")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationNicknameEntity {
    @Id
    private String nickname;
    @TimeToLive(unit = TimeUnit.MINUTES)
    private Long ttl;
}
