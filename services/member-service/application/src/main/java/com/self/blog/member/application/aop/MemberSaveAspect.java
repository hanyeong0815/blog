package com.self.blog.member.application.aop;

import com.self.blog.member.application.exception.MemberErrorCode;
import com.self.blog.common.utils.random.StrongStringRandom;
import com.self.blog.common.utils.time.ServerTime;
import com.self.blog.member.application.repository.*;
import com.self.blog.member.domain.*;
import com.self.blog.member.domain.type.SignType;
import com.self.blog.security.encoder.Sha256SaltedEncoderSupplier;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.time.Instant;

import static com.self.blog.common.utils.exception.Preconditions.validate;

@Component
@Aspect
@RequiredArgsConstructor
public class MemberSaveAspect {
    private final MemberRepository memberRepository;
    private final SignLogRepository signLogRepository;
    private final MemberRegistryDateTimeRepository memberRegistryDateTimeRepository;
    private final PasswordLastUpdateRepository passwordLastUpdateRepository;
    private final PasswordHistoryLogRepository passwordHistoryLogRepository;
    private final MemberStaticSaltRepository memberStaticSaltRepository;

    private final Sha256SaltedEncoderSupplier sha256SaltedEncoderSupplier;
    private final StrongStringRandom strongStringRandom;

    private final ServerTime serverTime;

    // 멤버 조회 후 이미 존재할 시 throw exception(회원 중복 가입 방지)
    @Before("@annotation(MemberSave) && args(member)")
    public void hasMember(Member member) {
        boolean hasMember = memberRepository.existsByUsername(member.username);
        validate(
                !hasMember,
                MemberErrorCode.USERNAME_ALREADY_USED
        );
    }

    // 회원가입 시 로그 남기기
    @AfterReturning(value = "@annotation(MemberSave)", returning = "member")
    public void saveSignLog(Member member) {
        Instant now = serverTime.nowInstant();

        SignLog signLog = SignLog.builder()
                .memberId(member.id)
                .username(member.username)
                .eventType(SignType.SIGN_UP)
                .remarks("회원가입")
                .createdAt(now)
                .build();

        signLogRepository.save(signLog);
    }

    // 회원가입 일자 저장
    @AfterReturning(value = "@annotation(MemberSave)", returning = "savedMember")
    public void savedAccountRegistryDatetime(Member savedMember) {
        Instant now = serverTime.nowInstant();

        MemberRegistryDatetime memberRegistryDatetime = MemberRegistryDatetime.builder()
                .memberId(savedMember.id)
                .createdAt(now)
                .build();

        memberRegistryDateTimeRepository.save(memberRegistryDatetime);
    }

    // password 마지막 변경 데이터 생성
    @AfterReturning(value = "@annotation(MemberSave)", returning = "savedMember")
    public void savePasswordLastUpdate(Member savedMember) {
        Instant now = serverTime.nowInstant();

        PasswordLastUpdate passwordLastUpdate = PasswordLastUpdate.builder()
                .memberId(savedMember.id)
                .username(savedMember.username)
                .createdAt(now)
                .build();

        passwordLastUpdateRepository.save(passwordLastUpdate);
    }

    // 회원의 password 이력 저장 로직
    @AfterReturning(
            value = "@annotation(MemberSave) && args(member)",
            returning = "savedMember",
            argNames = "member,savedMember"
    )
    public void savePasswordHistoryLog(Member member, Member savedMember) {
        Instant now = serverTime.nowInstant();

        // member 개개인의 static salt 조회 및 첫 생성 시 없을 경우 static salt 생성
        MemberStaticSalt memberStaticSalt = memberStaticSaltRepository
                .findTopByUsernameOrderByCreatedAt(member.username)
                .orElseGet(() -> {
                    String salt = strongStringRandom.nextString();
                    MemberStaticSalt newMemberStaticSalt = MemberStaticSalt.builder()
                            .memberId(savedMember.id)
                            .username(savedMember.username)
                            .staticSalt(salt)
                            .createdAt(now)
                            .build();

                    return memberStaticSaltRepository.save(newMemberStaticSalt);
                });

        String staticSalt = memberStaticSalt.staticSalt;
        String rawPassword = member.password;
        // rawPassword를 sha256encoder로 static salt를 사용하여 hashing
        String sha256Password = sha256SaltedEncoderSupplier
                .getEncoder(staticSalt)
                .encode(rawPassword);

        // hashing된 hashPassword 저장
        PasswordHistoryLog passwordHistoryLog = PasswordHistoryLog.builder()
                .memberId(savedMember.id)
                .username(savedMember.username)
                .personalSignedDigest(sha256Password)
                .createdAt(now)
                .build();

        passwordHistoryLogRepository.save(passwordHistoryLog);
    }
}
