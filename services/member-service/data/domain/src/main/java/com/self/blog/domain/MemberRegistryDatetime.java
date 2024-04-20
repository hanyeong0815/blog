package com.self.blog.domain;

import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class MemberRegistryDatetime {
    public Long id;
    public UUID memberId;
    public Instant createdAt;
}
