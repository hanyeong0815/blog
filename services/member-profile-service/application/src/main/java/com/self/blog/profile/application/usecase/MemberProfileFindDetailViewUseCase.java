package com.self.blog.profile.application.usecase;

import com.self.blog.profile.read_model.MemberProfileReadModels.MemberProfileDetailView;

public interface MemberProfileFindDetailViewUseCase {
    MemberProfileDetailView findDetailView(String username);
}
