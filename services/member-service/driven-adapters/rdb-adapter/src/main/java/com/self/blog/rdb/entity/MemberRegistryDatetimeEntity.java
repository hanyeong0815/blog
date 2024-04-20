package com.self.blog.rdb.entity;

import com.self.blog.jpa.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

import static com.self.blog.rdb.support.MemberSchemaConstants.SCHEMA;
import static com.self.blog.rdb.support.MemberSchemaConstants.TB_ACCOUNT_REGISTRY_DATETIME;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(
        schema = SCHEMA,
        name = TB_ACCOUNT_REGISTRY_DATETIME
)
public class AccountRegistryDatetimeEntity extends BaseEntity {
    public UUID memberId;
    public Instant createdAt;
}
