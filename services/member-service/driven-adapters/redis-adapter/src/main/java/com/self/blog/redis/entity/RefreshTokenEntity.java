package com.self.blog.redis.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

@Builder
@RedisHash(value = "refresh_token")
@NoArgsConstructor
@AllArgsConstructor
public class RefreshTokenEntity {
    @Id
    public String refreshToken;
    @Indexed
    public String subject;
    public Instant createdAt;
    @TimeToLive(unit = TimeUnit.MILLISECONDS)
    public Long ttl;
}
