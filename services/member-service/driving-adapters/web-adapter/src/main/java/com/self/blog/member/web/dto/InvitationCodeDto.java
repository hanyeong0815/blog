package com.self.blog.member.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record InvitationCodeDto() {
    public record InvitationCodeRequestDto(
            @Email
            @NotBlank
            String email
    ) {}

    public record CertifyInvitationCodeRequestDto(
            @Email
            @NotBlank
            String email,
            @NotBlank
            String invitationCode
    ) {}
}
