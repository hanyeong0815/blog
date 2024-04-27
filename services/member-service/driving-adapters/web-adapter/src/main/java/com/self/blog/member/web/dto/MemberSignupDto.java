package com.self.blog.member.web.dto;

import com.self.blog.member.domain.type.GenderType;
import com.self.blog.member.domain.type.MemberStatus;
import com.self.blog.member.domain.type.RoleType;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

import java.util.List;

import static com.self.blog.member.domain.policy.Members.InvalidationMessage.PASSWORD_MESSAGE;
import static com.self.blog.member.domain.policy.Members.InvalidationMessage.USERNAME_MESSAGE;
import static com.self.blog.member.domain.policy.Members.Validation.PASSWORD;
import static com.self.blog.member.domain.policy.Members.Validation.USERNAME;

public record MemberSignupDto() {
    public record MemberSignupRequestDto(
            @NotBlank
            @Pattern(regexp = USERNAME, message = USERNAME_MESSAGE)
            String username,
            @NotBlank
            @Pattern(regexp = PASSWORD, message = PASSWORD_MESSAGE)
            String password,
            String email,
            GenderType genderType,
            String name,
            String nickname,
            List<RoleType> roles
    ) {}

    @Builder
    public record MemberSignupResponseDto(
            String username,
            MemberStatus status
    ) {}
}
