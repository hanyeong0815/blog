package com.self.blog.rdb.entity;

import com.self.blog.domain.type.SignType;
import com.self.blog.jpa.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

import static com.self.blog.rdb.support.MemberSchemaConstants.SCHEMA;
import static com.self.blog.rdb.support.MemberSchemaConstants.TB_SIGN_LOG;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(
        schema = SCHEMA,
        catalog = SCHEMA,
        name = TB_SIGN_LOG
)
public class SignLogEntity extends BaseEntity {
    public UUID memberId;
    public String username;
    @Enumerated(EnumType.STRING)
    public SignType eventType;
    public String remarks;
    public Instant createdAt;
}
