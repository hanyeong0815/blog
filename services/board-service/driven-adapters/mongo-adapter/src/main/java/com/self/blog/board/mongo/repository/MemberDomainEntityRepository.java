package com.self.blog.board.mongo.repository;

import com.self.blog.board.mongo.entity.MemberDomainEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MemberDomainEntityRepository extends MongoRepository<MemberDomainEntity, String> {
    Optional<MemberDomainEntity> findByDomain(String domain);
}
