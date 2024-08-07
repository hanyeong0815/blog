package com.self.blog.member.application.usecase;

public interface CertifyInvitationCodeUseCase {
    boolean certifyInvitationCode(String invitationCode, String email);
}
