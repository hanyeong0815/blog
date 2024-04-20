package com.self.music.member.domain.type;

import com.google.gson.annotations.SerializedName;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum GenderType {
    @SerializedName("m")
    M("M"),
    @SerializedName("f")
    F("F");

    public final String genderType;
}
