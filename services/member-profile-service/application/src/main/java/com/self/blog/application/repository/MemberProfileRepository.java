package com.self.blog.application.repository;

import com.self.blog.domain.MemberProfile;

import java.util.Optional;
import java.util.UUID;

public interface MemberProfileRepository {
    MemberProfile save(MemberProfile memberProfile);
    Optional<MemberProfile> findByUsername(String username);
    Optional<MemberProfile> findByMemberId(UUID memberId);
}
