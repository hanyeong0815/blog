package com.self.blog.application.repository;

import com.self.blog.domain.MemberProfile;
import com.self.blog.read_model.MemberProfileReadModels.MemberProfileDetailView;
import com.self.blog.read_model.MemberProfileReadModels.MemberProfileNickname;

import java.util.Optional;
import java.util.UUID;

public interface MemberProfileRepository {
    MemberProfile save(MemberProfile memberProfile);
    Optional<MemberProfile> findByUsername(String username);
    Optional<MemberProfile> findByMemberId(UUID memberId);
    Optional<MemberProfileNickname> findNicknameByUsername(String username);
    Optional<MemberProfileDetailView> findDetailViewByUsername(String username);
    UUID findMemberIdByUsername(String username);
}
