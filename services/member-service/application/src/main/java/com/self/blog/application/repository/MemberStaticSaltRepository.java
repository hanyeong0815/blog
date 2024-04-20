package com.self.blog.application.repository;

import com.self.blog.domain.MemberStaticSalt;

import java.util.Optional;
import java.util.UUID;

public interface MemberStaticSaltRepository {
    MemberStaticSalt save(MemberStaticSalt memberStaticSalt);
    Optional<MemberStaticSalt> findById(UUID id);
    Optional<MemberStaticSalt> findByUsername(String username);

    Optional<MemberStaticSalt> findTopByUsernameOrderByCreatedAt(String username);
}
