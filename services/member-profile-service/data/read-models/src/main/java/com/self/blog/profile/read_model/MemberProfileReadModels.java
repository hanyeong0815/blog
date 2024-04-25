package com.self.blog.profile.read_model;

import com.self.blog.profile.domain.type.GenderType;
import lombok.Builder;

public record MemberProfileReadModels() {
    @Builder
    public record MemberProfileNickname(String nickname) {}

    @Builder
    public record MemberProfileDetailView(
            String username,
            String email,
            GenderType genderType,
            String name,
            String nickname
    ) {}
}
