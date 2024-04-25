package com.self.blog.member.rdb.entity;

import com.self.blog.jpa.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

import static com.self.blog.member.rdb.support.MemberSchemaConstants.SCHEMA;
import static com.self.blog.member.rdb.support.MemberSchemaConstants.TB_PASSWORD_LAST_UPDATE;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(
        schema = SCHEMA,
        catalog = SCHEMA,
        name = TB_PASSWORD_LAST_UPDATE
)
public class PasswordLastUpdateEntity extends BaseEntity {
    public UUID memberId;
    public String username;
    public Instant createdAt;
}
