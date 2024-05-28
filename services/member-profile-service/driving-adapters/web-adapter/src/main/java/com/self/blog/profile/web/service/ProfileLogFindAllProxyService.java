package com.self.blog.profile.web.service;

import com.self.blog.profile.application.usecase.ProfileLogFindAllUseCase;
import com.self.blog.profile.read_model.ProfileLogReadModels.ProfileLogDetailViewReadModel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileLogFindAllProxyService {
    private final ProfileLogFindAllUseCase profileLogFindAllUseCase;

    public List<ProfileLogDetailViewReadModel> findAll(Pageable pageable) {
        return profileLogFindAllUseCase.findAll(pageable);
    }
}
