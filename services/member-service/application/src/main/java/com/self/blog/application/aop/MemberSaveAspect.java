package com.self.blog.application.aop;

import com.self.blog.application.exception.MemberErrorCode;
import com.self.blog.application.repository.MemberRepository;
import com.self.blog.domain.Member;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import static com.self.blog.common.utils.exception.Preconditions.validate;

@Component
@Aspect
@RequiredArgsConstructor
public class MemberSaveAspect {
    private final MemberRepository memberRepository;
    
    @Before(value = "@annotation(HasMember) && args(member)")
    public void hasMember(Member member) {
        boolean hasMember = memberRepository.existsByUsername(member.username);
        validate(
                hasMember,
                MemberErrorCode.USERNAME_ALREADY_USED
        );
    }
}
