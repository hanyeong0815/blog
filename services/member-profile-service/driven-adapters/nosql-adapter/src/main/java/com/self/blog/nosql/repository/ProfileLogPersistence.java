package com.self.blog.nosql.repository;

import com.self.blog.application.repository.ProfileLogRepository;
import com.self.blog.domain.ProfileLog;
import com.self.blog.nosql.entity.ProfileLogEntity;
import com.self.blog.nosql.mapper.ProfileLogEntityMapper;
import com.self.blog.read_model.ProfileLogReadModels.ProfileLogDetailViewReadModel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ProfileLogPersistence implements ProfileLogRepository {
    private final ProfileLogMongoRepository repository;
    private final ProfileLogEntityMapper mapper;

    @Override
    public ProfileLog save(ProfileLog profileLog) {
        ProfileLogEntity profileLogEntity = mapper.toEntity(profileLog);
        return mapper.toDomain(repository.save(profileLogEntity));
    }

    @Override
    public List<ProfileLogDetailViewReadModel> findAll(Pageable pageable) {
        List<ProfileLogDetailViewReadModel> profileLogs = repository.findAllBy(pageable)
                .map(mapper::from)
                .getContent();

        System.out.println(profileLogs);

        return profileLogs;
    }

    @Override
    public List<ProfileLogDetailViewReadModel> findById(UUID memberId) {
        return repository.findByMemberId(memberId)
                .stream().map(mapper::from)
                .toList();
    }

    @Override
    public List<ProfileLogDetailViewReadModel> findByBeforeBetweenAfter(Instant startDateTime, Instant StartDateTime) {
        return repository.findByCreatedAtBetween(startDateTime, StartDateTime)
                .stream().map(mapper::from)
                .toList();
    }
}
