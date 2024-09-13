package com.self.blog.board.application.repository;

import com.self.blog.board.domain.MemberDomain;

import java.util.Optional;

public interface MemberDomainRepository {
    MemberDomain save(MemberDomain memberDomain);
    Optional<MemberDomain> findById(String id);
    Optional<MemberDomain> findByDomain(String domain);
}
