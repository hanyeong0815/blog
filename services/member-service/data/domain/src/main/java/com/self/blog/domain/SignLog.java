package com.self.blog.domain;

import com.self.blog.domain.type.SignType;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class SignLog {
    public Long id;
    public UUID memberId;
    public String username;
    public SignType eventType;
    public String remarks;
    public Instant createdAt;
}
