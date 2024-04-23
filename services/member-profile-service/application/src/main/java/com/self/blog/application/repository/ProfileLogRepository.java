package com.self.blog.application.repository;

import com.self.blog.domain.ProfileLog;
import com.self.blog.read_model.ProfileLogReadModels.ProfileLogDetailViewReadModel;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public interface ProfileLogRepository {
    ProfileLog save(ProfileLog profileLog);
    List<ProfileLogDetailViewReadModel> findAll(Pageable pageable);
    List<ProfileLogDetailViewReadModel> findById(UUID memberId);
    List<ProfileLogDetailViewReadModel> findByBeforeBetweenAfter(Instant before, Instant after);
}
