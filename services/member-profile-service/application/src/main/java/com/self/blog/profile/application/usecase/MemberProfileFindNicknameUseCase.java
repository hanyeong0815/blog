package com.self.blog.profile.application.usecase;

import com.self.blog.profile.read_model.MemberProfileReadModels.MemberProfileNickname;

public interface MemberProfileFindNicknameUseCase {
    MemberProfileNickname findNickname(String username);
}
