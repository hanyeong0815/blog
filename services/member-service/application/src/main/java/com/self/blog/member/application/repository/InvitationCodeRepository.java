package com.self.blog.member.application.repository;

import com.self.blog.member.domain.InvitationCode;

import java.util.Optional;

public interface InvitationCodeRepository {
    InvitationCode save(InvitationCode invitationCode);
    Optional<InvitationCode> findById(String invitationCode);
    Optional<InvitationCode> findByEmail(String email);
    void deleteById(String invitationCode);
}
