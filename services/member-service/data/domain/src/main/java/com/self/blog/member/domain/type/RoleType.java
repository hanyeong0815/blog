package com.self.blog.member.domain.type;

import com.google.gson.annotations.SerializedName;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum RoleType {
    @SerializedName("user")
    USER("USER"),
    @SerializedName("admin")
    ADMIN("ADMIN"),
    @SerializedName("test")
    TEST("TEST");

    public final String value;
}
