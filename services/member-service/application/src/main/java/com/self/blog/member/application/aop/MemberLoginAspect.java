package com.self.blog.member.application.aop;

import com.self.blog.member.application.exception.MemberErrorCode;
import com.self.blog.member.application.repository.MemberRepository;
import com.self.blog.member.application.repository.SignInTryRepository;
import com.self.blog.member.application.repository.SignLogRepository;
import com.self.blog.common.utils.time.ServerTime;
import com.self.blog.member.domain.SignInTry;
import com.self.blog.member.domain.SignLog;
import com.self.blog.member.domain.type.MemberStatus;
import com.self.blog.member.domain.type.SignType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.*;
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

    // 로그인 시도 log 저장
    @Before(value = "@annotation(MemberLogin) && args(authentication)", argNames = "authentication")
    public void saveMemberLoginTryLog(Authentication authentication) {
        Instant now = serverTime.nowInstant();

        String username = authentication.getName();
        // log 저장을 위한 memberId 조회 및 없을 시 throw exception
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

    // 로그인 때 exception 발생 시 로직
    @AfterThrowing(value = "@annotation(MemberLogin) && args(authentication)")
    public void countSignInTry(Authentication authentication) {
        String username = authentication.getName();
        boolean hasMember = memberRepository.existsByUsername(username);

        if (!hasMember) return; // member 조회 후 없을 시 아무 작업없이 return

        // 로그인 횟수 증가 TODO 로그인 횟수 제한을 통해 username을 유추할 수 있으므로 로직 삭제 고려
        SignInTry signInTry = signInTryRepository.countUpSignTry(username);

        // 로그인 실패 5회 이상 시 계정 보호 조치 로직
        if (signInTry.tryCount >= 5) {
            memberRepository.updateMemberStatus(username, MemberStatus.PROTECTED);

            UUID memberId = memberRepository.findIdByUsername(username).get().id(); // member 존재 여부는 위에서 확인 했으므로 바로 get

            // 계정 보호 조치 log 저장
            SignLog signLog = SignLog.builder()
                    .memberId(memberId)
                    .username(username)
                    .eventType(SignType.PROTECT)
                    .remarks("로그인 실패 5회 이상으로 보호 조치")
                    .createdAt(serverTime.nowInstant())
                    .build();

            signLogRepository.save(signLog);
            signInTryRepository.deleteById(username); // 로그인 횟수 초기화

            throw MemberErrorCode.PROTECTED_ACCOUNT.defaultException();
        }
    }

    // 로그인 성공 시 log 남기는 로직
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
