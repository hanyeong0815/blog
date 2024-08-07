package com.self.blog.member.rdb.entity;

import com.self.blog.jpa.UuidBaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

import static com.self.blog.member.rdb.support.MemberSchemaConstants.SCHEMA;
import static com.self.blog.member.rdb.support.MemberSchemaConstants.TB_MEMBER_STATIC_SALT;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(
        schema = SCHEMA,
        catalog = SCHEMA,
        name = TB_MEMBER_STATIC_SALT
)
public class MemberStaticSaltEntity extends UuidBaseEntity {
    private UUID memberId;
    private String username;
    private String staticSalt;
    private Instant createdAt;
}
