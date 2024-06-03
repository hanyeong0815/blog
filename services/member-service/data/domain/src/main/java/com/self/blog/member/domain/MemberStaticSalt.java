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
public class MemberStaticSalt {
    private UUID id;
    private UUID memberId;
    private String username;
    private String staticSalt;
    private Instant createdAt;
}
