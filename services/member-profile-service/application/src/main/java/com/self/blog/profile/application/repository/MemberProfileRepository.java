package com.self.blog.profile.application.repository;

import com.self.blog.profile.domain.MemberProfile;
import com.self.blog.profile.read_model.MemberProfileReadModels.MemberProfileDetailView;
import com.self.blog.profile.read_model.MemberProfileReadModels.MemberProfileIdReadModel;
import com.self.blog.profile.read_model.MemberProfileReadModels.MemberProfileNickname;

import java.util.Optional;
import java.util.UUID;

public interface MemberProfileRepository {
    MemberProfile save(MemberProfile memberProfile);
    boolean existsByNickname(String nickname);
    Optional<MemberProfile> findByUsername(String username);
    Optional<MemberProfile> findByMemberId(UUID memberId);
    Optional<MemberProfileNickname> findNicknameByUsername(String username);
    Optional<MemberProfileDetailView> findDetailViewByUsername(String username);
    UUID findMemberIdByUsername(String username);
    Optional<MemberProfileIdReadModel> findIdByUsername(String username);
}
