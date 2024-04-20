package com.self.blog.redis.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

@Builder
@RedisHash(value = "sign_in_try")
@NoArgsConstructor
@AllArgsConstructor
public class SignInTryEntity {
    @Id
    public String username;
    public int tryCount;
    public Instant firstTryTime;
    @TimeToLive(unit = TimeUnit.MILLISECONDS)
    public Long ttl;
}
