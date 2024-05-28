package com.self.blog.member.application.service;

import com.self.blog.member.application.aop.MemberSave;
import com.self.blog.member.application.aop.PasswordUpdate;
import com.self.blog.member.application.exception.MemberErrorCode;
import com.self.blog.member.application.repository.MemberRepository;
import com.self.blog.member.application.repository.RefreshTokenRepository;
import com.self.blog.member.application.usecase.MemberLoginUseCase;
import com.self.blog.member.application.usecase.MemberSignupUseCase;
import com.self.blog.member.application.usecase.PasswordUpdateUseCase;
import com.self.blog.member.application.usecase.data.JwtTokenPair;
import com.self.blog.common.utils.random.StrongStringRandom;
import com.self.blog.common.utils.time.ServerTime;
import com.self.blog.member.domain.Member;
import com.self.blog.member.domain.RefreshToken;
import com.self.blog.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

import static com.self.blog.common.utils.exception.Preconditions.validate;

@Service
@RequiredArgsConstructor
public class MemberService
        implements
        MemberSignupUseCase,
        UserDetailsService,
        MemberLoginUseCase,
        PasswordUpdateUseCase
{
    private final MemberRepository memberRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    private final JwtTokenProvider jwtTokenProvider;

    private final PasswordEncoder encoder;
    private final StrongStringRandom random;

    private final ServerTime serverTime;

    @Override
    @MemberSave // 회원가입 시 처리할 AOP 작업들과 연결을 위한 annotation
    public Member save(Member member) {
        // hashing
        String hashPassword = encoder.encode(member.password);
        // toBuilder -> member의 데이터를 clone 후 변경할 부분만 변경
        Member cloneMember = member.toBuilder()
                .password(hashPassword)
                .build();
        return memberRepository.save(cloneMember);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return memberRepository.findByUsername(username)
                .map(this::createUserDetails)
                .orElseThrow(
                        MemberErrorCode.NO_SUCH_USER::defaultException
                );
    }

    private UserDetails createUserDetails(Member member) {
        return User.builder()
                .username(member.username)
                .password(member.password)
                .roles(member.roles.stream().map(role -> role.value).toArray(String[]::new))
                .build();
    }

    @Override
    public JwtTokenPair login(Authentication authentication) {
        // TODO 현재시간이 안맞음 / annotation을 활용한 현재시간을 받는 방법 고려
        Instant now = serverTime.nowInstant();

        String accessToken = jwtTokenProvider.generateToken(authentication); // accessToken 발행
        String refreshToken = random.nextString(); // refreshToken 발행 -> TODO refreshToken도 JWT형식으로 발행할 필요가 있는지 고려

        // refreshToken에관한 정보 redis에 저장
        RefreshToken refreshTokenDomain = RefreshToken.builder()
                .refreshToken(refreshToken)
                .subject(authentication.getName())
                .createdAt(now)
                .ttl(2_628_000L)
                .build();

        refreshTokenRepository.save(refreshTokenDomain);

        return JwtTokenPair.builder()
                .accessToken(STR."Bearer \{accessToken}") // Java21의 기능을 활요한 String format
                .refreshToken(refreshToken)
                .build();
    }

    @PasswordUpdate
    @Override
    public boolean updatePassword(String username, String password) {
        // member 존재 여부 확인 후 없을 시 throw exception
        boolean hasMember = memberRepository.existsByUsername(username);
        validate(
                hasMember,
                MemberErrorCode.NO_SUCH_USER
        );

        return memberRepository.updateMemberPassword(username, encoder.encode(password));
    }
}
