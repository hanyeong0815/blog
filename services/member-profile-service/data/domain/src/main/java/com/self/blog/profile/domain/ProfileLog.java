package com.self.blog.profile.domain;

import com.self.blog.profile.domain.type.ProfileLogType;
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
public class ProfileLog {
    private String id;
    private UUID memberId;
    private String username;
    private ProfileLogType logType;
    private String remark;
    private Instant createdAt;
}
