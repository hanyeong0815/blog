package com.self.blog.rdb.projection;

import java.util.UUID;

public record MemberProjections() {
    public record MemberIdProjection(UUID id) {}
}
