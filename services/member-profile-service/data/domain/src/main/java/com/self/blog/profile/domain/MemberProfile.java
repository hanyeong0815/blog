package com.self.blog.profile.domain;

import com.self.blog.profile.domain.type.GenderType;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class MemberProfile {
    private Long id;
    private UUID memberId;
    private String username;
    private String email;
    private GenderType genderType;
    private String name;
    private String nickname;
}
