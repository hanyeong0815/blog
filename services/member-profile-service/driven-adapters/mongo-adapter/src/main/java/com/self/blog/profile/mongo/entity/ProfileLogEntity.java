package com.self.blog.profile.mongo.entity;

import com.self.blog.profile.domain.type.ProfileLogType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.UUID;

@Document("profile_log")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProfileLogEntity {
    @Id
    public String id;
    @Indexed
    public UUID memberId;
    @Indexed
    public String username;
    public ProfileLogType logType;
    public String remark;
    @Indexed
    public Instant createdAt;
}
