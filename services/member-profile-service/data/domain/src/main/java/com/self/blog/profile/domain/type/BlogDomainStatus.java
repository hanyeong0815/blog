package com.self.blog.profile.domain.type;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum BlogDomainStatus {
    ACTIVE("활성화", true),
    UPDATE("변경됨", false),
    DELETE("삭제됨", false);


    public final String message;
    public final boolean state;
}
