package com.self.blog.application.usecase;

import com.self.blog.read_model.MemberProfileReadModels.MemberProfileDetailView;

public interface MemberProfileFindDetailViewUseCase {
    MemberProfileDetailView findDetailView(String username);
}
