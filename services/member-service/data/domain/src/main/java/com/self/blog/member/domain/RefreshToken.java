package com.self.blog.member.domain;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RefreshToken {
    private String refreshToken;
    private String subject;
    private Instant createdAt;
    private Long ttl;
}
