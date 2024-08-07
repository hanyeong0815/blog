package com.self.blog.profile.rdb.entity;

import com.self.blog.profile.domain.type.GenderType;
import com.self.blog.jpa.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

import static com.self.blog.profile.rdb.support.ProfileSchemaConstants.SCHEMA;
import static com.self.blog.profile.rdb.support.ProfileSchemaConstants.TB_PROFILE;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(
        schema = SCHEMA,
        catalog = SCHEMA,
        name = TB_PROFILE
)
public class MemberProfileEntity extends BaseEntity {
    private UUID memberId;
    private String username;
    private String email;
    @Enumerated(EnumType.STRING)
    private GenderType genderType;
    private String name;
    private String nickname;
}
