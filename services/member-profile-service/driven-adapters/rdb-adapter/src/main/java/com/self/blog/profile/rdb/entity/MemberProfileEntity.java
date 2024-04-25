package com.self.blog.profile.rdb.entity;

import com.self.blog.profile.domain.type.GenderType;
import com.self.blog.jpa.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.UUID;

import static com.self.blog.profile.rdb.support.ProfileSchemaConstants.SCHEMA;
import static com.self.blog.profile.rdb.support.ProfileSchemaConstants.TB_PROFILE;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(
        schema = SCHEMA,
        catalog = SCHEMA,
        name = TB_PROFILE
)
public class MemberProfileEntity extends BaseEntity {
    public UUID memberId;
    public String username;
    public String email;
    @Enumerated(EnumType.STRING)
    public GenderType genderType;
    public String name;
    public String nickname;
}
