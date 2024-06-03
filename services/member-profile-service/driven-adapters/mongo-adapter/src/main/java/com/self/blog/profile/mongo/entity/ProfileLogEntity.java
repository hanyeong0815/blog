package com.self.blog.profile.mongo.entity;

import com.self.blog.profile.domain.type.ProfileLogType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.UUID;

@Document("profile_log")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProfileLogEntity {
    @Id
    private String id;
    @Indexed
    private UUID memberId;
    @Indexed
    private String username;
    private ProfileLogType logType;
    private String remark;
    @Indexed
    private Instant createdAt;
}
