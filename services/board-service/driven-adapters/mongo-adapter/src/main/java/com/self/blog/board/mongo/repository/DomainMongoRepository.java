package com.self.blog.board.mongo.repository;

import com.self.blog.board.mongo.entity.DomainEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface DomainMongoRepository extends MongoRepository<DomainEntity, String> {
    Optional<DomainEntity> findByDomain(String domain);
    boolean existsByDomain(String category);
}
