package com.self.blog.read_model;

import com.self.blog.domain.type.GenderType;
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
