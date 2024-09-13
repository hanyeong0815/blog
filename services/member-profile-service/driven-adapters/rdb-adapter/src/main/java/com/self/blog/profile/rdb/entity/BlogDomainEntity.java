package com.self.blog.profile.rdb.entity;

import com.self.blog.jpa.BaseEntity;
import com.self.blog.profile.domain.type.BlogDomainStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.*;

import static com.self.blog.profile.rdb.support.ProfileSchemaConstants.SCHEMA;
import static com.self.blog.profile.rdb.support.ProfileSchemaConstants.TB_BLOG_DOMAIN;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(
        schema = SCHEMA,
        catalog = SCHEMA,
        name = TB_BLOG_DOMAIN
)
public class BlogDomainEntity extends BaseEntity {
    private Long profileId;
    private String domain;
    @Enumerated(EnumType.STRING)
    private BlogDomainStatus status;
}
