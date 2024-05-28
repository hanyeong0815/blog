package com.self.blog.member.application.usecase;

import com.self.blog.member.domain.Member;

public interface MemberSignupUseCase {
    Member save(Member member);
}
