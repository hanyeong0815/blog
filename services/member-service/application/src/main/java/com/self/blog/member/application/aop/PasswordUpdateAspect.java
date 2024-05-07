package com.self.blog.member.application.aop;

import com.self.blog.common.utils.time.ServerTime;
import com.self.blog.member.application.exception.MemberErrorCode;
import com.self.blog.member.application.repository.MemberRepository;
import com.self.blog.member.application.repository.MemberStaticSaltRepository;
import com.self.blog.member.application.repository.PasswordHistoryLogRepository;
import com.self.blog.member.application.repository.PasswordLastUpdateRepository;
import com.self.blog.member.domain.MemberStaticSalt;
import com.self.blog.member.domain.PasswordHistoryLog;
import com.self.blog.security.encoder.Sha256SaltedEncoder;
import com.self.blog.security.encoder.Sha256SaltedEncoderSupplier;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import static com.self.blog.common.utils.exception.Preconditions.validate;

@Aspect
@Component
@RequiredArgsConstructor
public class PasswordUpdateAspect {
    private final MemberRepository memberRepository;
    private final MemberStaticSaltRepository memberStaticSaltRepository;
    private final PasswordHistoryLogRepository passwordHistoryLogRepository;
    private final PasswordLastUpdateRepository passwordLastUpdateRepository;

    private final Sha256SaltedEncoderSupplier sha256SaltedEncoderSupplier;

    private final ServerTime serverTime;

    @Before(value = "@annotation(PasswordUpdate) && args(username, password)", argNames = "username,password")
    public void verifyPasswordHistory(String username, String password) {
        MemberStaticSalt memberStaticSalt = memberStaticSaltRepository.findTopByUsernameOrderByCreatedAt(username)
                .orElseThrow(MemberErrorCode.DEFAULT::defaultException);

        String staticSalt = memberStaticSalt.staticSalt;
        Sha256SaltedEncoder encoder = sha256SaltedEncoderSupplier.getEncoder(staticSalt);

        List<String> passwordHistories = passwordHistoryLogRepository.findByUsername(username);
        for (String passwordHistory : passwordHistories) {
            validate(
                    encoder.matches(password, passwordHistory),
                    MemberErrorCode.PASSWORD_ALREADY_USED
            );
        }
    }

    @After(value = "@annotation(PasswordUpdate) && args(username, password)", argNames = "username,password")
    public void updatePasswordLastUpdate(String username, String password) {
        Instant now = serverTime.nowInstant();
        passwordLastUpdateRepository.updateCreatedAt(username, now);

        MemberStaticSalt memberStaticSalt = memberStaticSaltRepository.findTopByUsernameOrderByCreatedAt(username)
                .orElseThrow(MemberErrorCode.DEFAULT::defaultException);

        String staticSalt = memberStaticSalt.staticSalt;
        String sha256Password = sha256SaltedEncoderSupplier.getEncoder(staticSalt).encode(password);
        UUID memberId = memberRepository.findIdByUsername(username).orElseThrow(
                MemberErrorCode.NO_SUCH_USER::defaultException
        ).id();

        PasswordHistoryLog passwordHistoryLog = PasswordHistoryLog.builder()
                .memberId(memberId)
                .username(username)
                .personalSignedDigest(sha256Password)
                .createdAt(now)
                .build();

        passwordHistoryLogRepository.save(passwordHistoryLog);
    }
}
