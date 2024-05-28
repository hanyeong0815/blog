package com.self.blog.member.rdb.projection;

public record PasswordHistoryLogProjection() {
    public record PasswordHistoryLogsProjection(String personalSignedDigest) {}
}
