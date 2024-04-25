package com.self.blog.member.application.repository;

import com.self.blog.member.domain.MemberRegistryDatetime;

import java.util.Optional;
import java.util.UUID;

public interface MemberRegistryDateTimeRepository {
    MemberRegistryDatetime save(MemberRegistryDatetime memberRegistryDatetime);
    Optional<MemberRegistryDatetime> findById(Long id);
    Optional<MemberRegistryDatetime> findByMemberId(UUID memberId);
}
