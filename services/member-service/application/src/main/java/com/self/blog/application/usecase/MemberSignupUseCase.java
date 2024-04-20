package com.self.blog.application.usecase;

import com.self.blog.domain.Member;

public interface SignupUseCase {
    Member save(Member member);
}
