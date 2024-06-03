package com.self.blog.member.domain;

import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class MemberRegistryDatetime {
    private Long id;
    private UUID memberId;
    private Instant createdAt;
}
