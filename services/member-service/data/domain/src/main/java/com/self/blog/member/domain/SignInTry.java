package com.self.blog.member.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignInTry {
    public String username;
    public int tryCount;
    public Instant firstTryTime;
    public Long ttl;
}
