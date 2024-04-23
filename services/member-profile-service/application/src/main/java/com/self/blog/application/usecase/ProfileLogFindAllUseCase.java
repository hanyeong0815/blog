package com.self.blog.application.usecase;

import com.self.blog.read_model.ProfileLogReadModels.ProfileLogDetailViewReadModel;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProfileLogFindAllUseCase {
    List<ProfileLogDetailViewReadModel> findAll(Pageable pageable);
}
