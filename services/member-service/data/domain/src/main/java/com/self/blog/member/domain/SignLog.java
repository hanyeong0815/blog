package com.self.blog.member.domain;

import com.self.blog.member.domain.type.SignType;
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
public class SignLog {
    private Long id;
    private UUID memberId;
    private String username;
    private SignType eventType;
    private String remarks;
    private Instant createdAt;
}
