package com.self.blog.domain;

import com.self.blog.domain.type.MemberStatus;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Member {
    public UUID id;
    public String username;
    public String password;
    public MemberStatus status;
    public List<String> roles;
}
