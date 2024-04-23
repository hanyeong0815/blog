package com.self.blog.domain.type;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ProfileLogType {
    CREATE("가입"),
    VERIFY("검색"),
    MODIFY("수정"),
    DELETE("삭제");

    public final String remark;
}
