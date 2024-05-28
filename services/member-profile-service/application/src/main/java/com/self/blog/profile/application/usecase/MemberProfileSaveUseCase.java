package com.self.blog.profile.application.usecase;

import com.self.blog.profile.domain.MemberProfile;

public interface MemberProfileSaveUseCase {
    MemberProfile save(MemberProfile memberProfile);
}
