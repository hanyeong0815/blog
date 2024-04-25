package com.self.blog.profile.domain;

import com.self.blog.profile.domain.type.ProfileLogType;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class ProfileLog {
    public String id;
    public UUID memberId;
    public String username;
    public ProfileLogType logType;
    public String remark;
    public Instant createdAt;
}
