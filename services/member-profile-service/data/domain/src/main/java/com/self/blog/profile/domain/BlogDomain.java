package com.self.blog.profile.domain;

import com.self.blog.profile.domain.type.BlogDomainStatus;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class BlogDomain {
    private Long id;
    private Long profileId;
    private String domain;
    private BlogDomainStatus status;
}
