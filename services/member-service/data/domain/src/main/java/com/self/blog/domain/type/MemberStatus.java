package com.self.blog.domain.type;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum MemberStatus {
    PENDING(false),
    ACTIVE(true),
    PROTECTED(false),
    SUSPENDED(false),
    SLEPT(false),
    REMOVED(false);

    public final boolean canSignIn;
}
