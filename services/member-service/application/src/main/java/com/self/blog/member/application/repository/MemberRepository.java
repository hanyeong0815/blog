package com.self.blog.member.application.repository;

import com.self.blog.member.domain.Member;
import com.self.blog.member.domain.type.MemberStatus;
import com.self.blog.member.readmodel.MemberReadModels.MemberIdReadModel;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface MemberRepository {
    Member save(Member member);
    void deleteById(UUID id);
    Optional<Member> findByUsername(String username);
    Collection<? extends GrantedAuthority> findRolesByUsername(String username);
    boolean existsByUsername(String username);
    Optional<MemberIdReadModel> findIdByUsername(String name);
    boolean updateMemberStatus(String username, MemberStatus memberStatus);
    boolean updateMemberPassword(String username, String password);
    Collection<? extends GrantedAuthority> getAuthorities(Member member);
}
