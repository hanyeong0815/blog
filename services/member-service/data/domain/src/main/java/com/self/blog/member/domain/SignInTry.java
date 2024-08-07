package com.self.blog.member.domain;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignInTry {
    private String username;
    private int tryCount;
    private Instant firstTryTime;
    private Long ttl;
}
