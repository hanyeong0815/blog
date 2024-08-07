package com.self.blog.member.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class InvitationCode {
    private String invitationCode;
    private String email;
    private Boolean isVerified;
    private Long ttl;
}
