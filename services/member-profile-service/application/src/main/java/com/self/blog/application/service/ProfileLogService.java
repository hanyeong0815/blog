package com.self.blog.application.service;

import com.self.blog.application.repository.ProfileLogRepository;
import com.self.blog.application.usecase.ProfileLogFindAllUseCase;
import com.self.blog.read_model.ProfileLogReadModels.ProfileLogDetailViewReadModel;
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
