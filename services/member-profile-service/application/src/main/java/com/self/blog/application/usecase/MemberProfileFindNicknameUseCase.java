package com.self.blog.application.usecase;

import com.self.blog.read_model.MemberProfileReadModels.MemberProfileNickname;

public interface MemberProfileFindNicknameUseCase {
    MemberProfileNickname findNickname(String username);
}
