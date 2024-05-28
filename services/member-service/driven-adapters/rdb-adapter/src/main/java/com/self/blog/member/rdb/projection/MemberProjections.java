package com.self.blog.member.rdb.projection;

import java.util.UUID;

public record MemberProjections() {
    public record MemberIdProjection(UUID id) {}
}
