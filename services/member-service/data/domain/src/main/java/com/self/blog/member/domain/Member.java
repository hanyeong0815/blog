package com.self.blog.member.domain;

import com.self.blog.member.domain.type.MemberStatus;
import com.self.blog.member.domain.type.RoleType;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Member {
    private UUID id;
    private String username;
    private String password;
    private MemberStatus status;
    private List<RoleType> roles;
}
