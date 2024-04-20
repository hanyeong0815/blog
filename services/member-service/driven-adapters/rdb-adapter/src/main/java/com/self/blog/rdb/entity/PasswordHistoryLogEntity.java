package com.self.blog.rdb.entity;

import com.self.blog.jpa.UuidBaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

import static com.self.blog.rdb.support.MemberSchemaConstants.SCHEMA;
import static com.self.blog.rdb.support.MemberSchemaConstants.TB_PASSWORD_HISTORY_LOG;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(
        schema = SCHEMA,
        catalog = SCHEMA,
        name = TB_PASSWORD_HISTORY_LOG
)
public class PasswordHistoryLogEntity extends UuidBaseEntity {
    public UUID memberId;
    public String username;
    public String personalSignedDigest;
    public Instant createdAt;
}
