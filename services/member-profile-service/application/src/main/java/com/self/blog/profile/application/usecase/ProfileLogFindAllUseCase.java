package com.self.blog.profile.application.usecase;

import com.self.blog.profile.read_model.ProfileLogReadModels.ProfileLogDetailViewReadModel;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProfileLogFindAllUseCase {
    List<ProfileLogDetailViewReadModel> findAll(Pageable pageable);
}
