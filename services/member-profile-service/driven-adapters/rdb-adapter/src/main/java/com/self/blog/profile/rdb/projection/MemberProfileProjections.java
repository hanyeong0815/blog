package com.self.blog.profile.rdb.projection;

import com.self.blog.profile.domain.type.GenderType;

import java.util.UUID;

public record MemberProfileProjections() {
    public record MemberProfileNicknameProjection(String nickname) {}

    public record MemberProfileDetailViewProjection(
            String username,
            String email,
            GenderType genderType,
            String name,
            String nickname
    ) {}

    public record MemberProfileMemberIdProjection(UUID memberId) {}
}
