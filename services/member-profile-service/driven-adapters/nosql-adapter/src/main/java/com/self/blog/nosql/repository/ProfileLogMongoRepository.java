package com.self.blog.nosql.repository;

import com.self.blog.nosql.entity.ProfileLogEntity;
import com.self.blog.nosql.projection.ProfileLogProjections.ProfileLogDetailViewProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public interface ProfileLogMongoRepository extends MongoRepository<ProfileLogEntity, String> {
    Page<ProfileLogDetailViewProjection> findAllBy(Pageable pageable);
    List<ProfileLogDetailViewProjection> findByMemberId(UUID memberId);
    List<ProfileLogDetailViewProjection> findByCreatedAtBetween(Instant startDateTime, Instant endDateTime);
}
