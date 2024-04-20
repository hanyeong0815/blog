package com.self.music.passwordEncoder.type;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

public enum EncoderType {
    @JsonProperty("bcrypt")
    @SerializedName("bcrypt")
    BCRYPT("{bcrypt}"),
    @JsonProperty("scrypt")
    @SerializedName("scrypt")
    SCRYPT("{scrypt}"),
    @JsonProperty("pbkdf2")
    @SerializedName("pbkdf2")
    PBKDF2("{pbkdf2}"),
    @JsonProperty("no_option") // key로도 value로도 사용 가능하도록 snake case 채택
    @SerializedName("no_option")
    NOOP("{noop}");

    final String value;

    EncoderType(String bracketExpression) {
        this.value = bracketExpression;
    }

    public static EncoderType fromBracketExpression(String bracketExpression) {
        if (bracketExpression == null) throw new NullPointerException();

        for (var item: values()) {
            if (bracketExpression.equals(item.value)) {
                return item;
            }
        }

        throw new IllegalArgumentException();
    }
}
