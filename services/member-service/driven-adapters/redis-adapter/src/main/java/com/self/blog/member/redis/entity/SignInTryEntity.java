package com.self.blog.member.redis.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

@Getter
@Setter
@Builder
@RedisHash(value = "sign_in_try")
@NoArgsConstructor
@AllArgsConstructor
public class SignInTryEntity {
    @Id
    private String username;
    private int tryCount;
    private Instant firstTryTime;
    @TimeToLive(unit = TimeUnit.MILLISECONDS)
    private Long ttl;
}
