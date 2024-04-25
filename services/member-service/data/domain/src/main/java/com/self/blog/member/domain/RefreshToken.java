package com.self.blog.member.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RefreshToken {
    public String refreshToken;
    public String subject;
    public Instant createdAt;
    public Long ttl;
}
