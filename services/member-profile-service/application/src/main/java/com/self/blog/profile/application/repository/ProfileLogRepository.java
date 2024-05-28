package com.self.blog.profile.application.repository;

import com.self.blog.profile.domain.ProfileLog;
import com.self.blog.profile.read_model.ProfileLogReadModels.ProfileLogDetailViewReadModel;
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
