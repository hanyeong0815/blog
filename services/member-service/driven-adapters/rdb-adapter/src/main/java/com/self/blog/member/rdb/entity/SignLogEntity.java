package com.self.blog.member.rdb.entity;

import com.self.blog.member.domain.type.SignType;
import com.self.blog.jpa.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

import static com.self.blog.member.rdb.support.MemberSchemaConstants.SCHEMA;
import static com.self.blog.member.rdb.support.MemberSchemaConstants.TB_SIGN_LOG;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(
        schema = SCHEMA,
        catalog = SCHEMA,
        name = TB_SIGN_LOG
)
public class SignLogEntity extends BaseEntity {
    private UUID memberId;
    private String username;
    @Enumerated(EnumType.STRING)
    private SignType eventType;
    private String remarks;
    private Instant createdAt;
}
