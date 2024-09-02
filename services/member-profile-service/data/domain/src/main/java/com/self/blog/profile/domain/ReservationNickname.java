package com.self.blog.profile.domain;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class ReservationNickname {
    private String nickname;
    private Long ttl;
}
