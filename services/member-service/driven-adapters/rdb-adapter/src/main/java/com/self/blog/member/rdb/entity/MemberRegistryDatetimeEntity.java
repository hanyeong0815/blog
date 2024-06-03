package com.self.blog.member.rdb.entity;

import com.self.blog.jpa.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

import static com.self.blog.member.rdb.support.MemberSchemaConstants.SCHEMA;
import static com.self.blog.member.rdb.support.MemberSchemaConstants.TB_MEMBER_REGISTRY_DATETIME;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(
        schema = SCHEMA,
        catalog = SCHEMA,
        name = TB_MEMBER_REGISTRY_DATETIME
)
public class MemberRegistryDatetimeEntity extends BaseEntity {
    private UUID memberId;
    private Instant createdAt;
}
