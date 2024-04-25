package com.self.blog.profile.application.service;

import com.self.blog.profile.application.repository.ProfileLogRepository;
import com.self.blog.profile.application.usecase.ProfileLogFindAllUseCase;
import com.self.blog.profile.read_model.ProfileLogReadModels.ProfileLogDetailViewReadModel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileLogService implements ProfileLogFindAllUseCase {
    private final ProfileLogRepository profileLogRepository;

    @Override
    public List<ProfileLogDetailViewReadModel> findAll(Pageable pageable) {
        return profileLogRepository.findAll(pageable);
    }
}
