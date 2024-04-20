package com.self.blog.domain;

import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class MemberStaticSalt {
    public UUID id;
    public UUID memberId;
    public String username;
    public String staticSalt;
    public Instant createdAt;
}
