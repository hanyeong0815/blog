package com.self.blog.member.application.repository;

import com.self.blog.member.domain.Member;
import com.self.blog.member.domain.type.MemberStatus;
import com.self.blog.member.readmodel.MemberReadModels.MemberIdReadModel;

import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findByUsername(String username);
    boolean existsByUsername(String username);
    Optional<MemberIdReadModel> findIdByUsername(String name);
    boolean updateMemberStatus(String username, MemberStatus memberStatus);
    boolean updateMemberPassword(String username, String password);
}
