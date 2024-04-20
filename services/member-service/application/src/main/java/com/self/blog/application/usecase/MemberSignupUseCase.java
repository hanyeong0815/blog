package com.self.blog.application.usecase;

import com.self.blog.domain.Member;

public interface MemberSignupUseCase {
    Member save(Member member);
}
