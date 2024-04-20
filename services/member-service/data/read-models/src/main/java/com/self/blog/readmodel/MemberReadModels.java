package com.self.blog.readmodel;

import java.util.UUID;

public record MemberReadModels() {
    public record MemberIdReadModel(UUID id) {}
}
