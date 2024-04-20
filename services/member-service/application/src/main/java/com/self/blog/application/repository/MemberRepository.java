package com.self.blog.application.repository;

import com.self.blog.domain.Member;
import com.self.blog.domain.type.MemberStatus;
import com.self.blog.readmodel.MemberReadModels.MemberIdReadModel;

import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findByUsername(String username);
    boolean existsByUsername(String username);
    Optional<MemberIdReadModel> findIdByUsername(String name);
    boolean updateMemberStatus(String username, MemberStatus memberStatus);
}
