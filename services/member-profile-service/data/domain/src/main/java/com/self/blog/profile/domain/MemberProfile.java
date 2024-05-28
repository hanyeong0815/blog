package com.self.blog.profile.domain;

import com.self.blog.profile.domain.type.GenderType;
import lombok.*;

import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class MemberProfile {
    public Long id;
    public UUID memberId;
    public String username;
    public String email;
    public GenderType genderType;
    public String name;
    public String nickname;
}
