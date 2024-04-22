package com.self.blog.rdb.projection;

import com.self.blog.domain.type.GenderType;

public record MemberProfileProjections() {
    public record MemberProfileNicknameProjection(String nickname) {}

    public record MemberProfileDetailViewProjection(
            String username,
            String email,
            GenderType genderType,
            String name,
            String nickname
    ) {}
}
