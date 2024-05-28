package com.self.blog.member.readmodel;

import java.util.UUID;

public record MemberReadModels() {
    public record MemberIdReadModel(UUID id) {}
}
