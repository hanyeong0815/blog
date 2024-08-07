package com.self.blog.member.redis.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

import java.util.concurrent.TimeUnit;

@Getter
@Builder
@RedisHash(value = "invitation_code")
@NoArgsConstructor
@AllArgsConstructor
public class InvitationCodeEntity {
    @Id
    private String invitationCode;
    @Indexed
    private String email;
    private Boolean isVerified;
    @TimeToLive(unit = TimeUnit.MILLISECONDS)
    private Long ttl;
}
