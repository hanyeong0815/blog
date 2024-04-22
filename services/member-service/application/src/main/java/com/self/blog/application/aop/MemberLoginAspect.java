package com.self.blog.application.aop;

import com.self.blog.application.exception.MemberErrorCode;
import com.self.blog.application.repository.MemberRepository;
import com.self.blog.application.repository.SignInTryRepository;
import com.self.blog.application.repository.SignLogRepository;
import com.self.blog.common.utils.time.ServerTime;
import com.self.blog.domain.SignInTry;
import com.self.blog.domain.SignLog;
import com.self.blog.domain.type.MemberStatus;
import com.self.blog.domain.type.SignType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.*;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class MemberLoginAspect {
    private final MemberRepository memberRepository;
    private final SignLogRepository signLogRepository;
    private final SignInTryRepository signInTryRepository;

    private final ServerTime serverTime;

    @Before(value = "@annotation(MemberLogin) && args(authentication)", argNames = "authentication")
    public void saveMemberLoginTryLog(Authentication authentication) {
        Instant now = serverTime.nowInstant();

        String username = authentication.getName();
        UUID memberId = memberRepository
                .findIdByUsername(username)
                .orElseThrow(
                        MemberErrorCode.INVALID_USERNAME_OR_PASSWORD::defaultException
                ).id();

        SignLog signLog = SignLog.builder()
                .memberId(memberId)
                .username(username)
                .eventType(SignType.SIGN_IN_TRY)
                .remarks("로그인 시도")
                .createdAt(now)
                .build();

        signLogRepository.save(signLog);
    }

    @AfterThrowing(value = "@annotation(MemberLogin) && args(authentication)")
    public void countSignInTry(Authentication authentication) {
        String username = authentication.getName();
        boolean hasMember = memberRepository.existsByUsername(username);

        if (!hasMember) return;

        SignInTry signInTry = signInTryRepository.countUpSignTry(username);

        if (signInTry.tryCount >= 5) {
            memberRepository.updateMemberStatus(username, MemberStatus.PROTECTED);

            UUID memberId = memberRepository.findIdByUsername(username).get().id();

            SignLog signLog = SignLog.builder()
                    .memberId(memberId)
                    .username(username)
                    .eventType(SignType.PROTECT)
                    .remarks("로그인 실패 5회 이상으로 보호 조치")
                    .createdAt(serverTime.nowInstant())
                    .build();

            signLogRepository.save(signLog);
            signInTryRepository.deleteById(username);

            throw MemberErrorCode.PROTECTED_ACCOUNT.defaultException();
        }
    }

    @AfterReturning("@annotation(MemberLogin) && args(authentication)")
    public void successLogin(Authentication authentication) {
        String username = authentication.getName();
        UUID memberId = memberRepository.findIdByUsername(username)
                .orElseThrow(
                        MemberErrorCode.NO_SUCH_USER::defaultException
                )
                .id();

        SignLog signLog = SignLog.builder()
                .memberId(memberId)
                .username(username)
                .eventType(SignType.SIGN_IN_ACCEPT)
                .remarks("로그인")
                .createdAt(serverTime.nowInstant())
                .build();

        signInTryRepository.deleteById(username);
        signLogRepository.save(signLog);
    }
}
