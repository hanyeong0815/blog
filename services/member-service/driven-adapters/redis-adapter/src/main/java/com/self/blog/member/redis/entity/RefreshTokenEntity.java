package com.self.blog.member.redis.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

@Getter
@Setter
@Builder
@RedisHash(value = "refresh_token")
@NoArgsConstructor
@AllArgsConstructor
public class RefreshTokenEntity {
    @Id
    private String refreshToken;
    @Indexed
    private String subject;
    private Instant createdAt;
    @TimeToLive(unit = TimeUnit.MILLISECONDS)
    private Long ttl;
}
