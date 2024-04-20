package com.self.blog.domain;

import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class PasswordHistoryLog {
    public UUID id;
    public UUID memberId;
    public String username;
    public String personalSignedDigest;
    public Instant createdAt;
}
