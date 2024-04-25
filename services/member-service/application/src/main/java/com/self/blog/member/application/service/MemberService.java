package com.self.blog.member.application.service;

import com.self.blog.member.application.aop.MemberSave;
import com.self.blog.member.application.authentication.jwt.JwtTokenProvider;
import com.self.blog.member.application.exception.MemberErrorCode;
import com.self.blog.member.application.repository.MemberRepository;
import com.self.blog.member.application.repository.RefreshTokenRepository;
import com.self.blog.member.application.usecase.MemberLoginUseCase;
import com.self.blog.member.application.usecase.MemberSignupUseCase;
import com.self.blog.member.application.usecase.data.JwtTokenPair;
import com.self.blog.common.utils.random.StrongStringRandom;
import com.self.blog.common.utils.time.ServerTime;
import com.self.blog.member.domain.Member;
import com.self.blog.member.domain.RefreshToken;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class MemberService
        implements
        MemberSignupUseCase,
        UserDetailsService,
        MemberLoginUseCase
{
    private final MemberRepository memberRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    private final JwtTokenProvider jwtTokenProvider;

    private final PasswordEncoder encoder;
    private final StrongStringRandom random;

    private final ServerTime serverTime;

    @Override
    @MemberSave
    public Member save(Member member) {
        String hashPassword = encoder.encode(member.password);
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
                .roles(member.roles.toArray(String[]::new))
                .build();
    }

    @Override
    public JwtTokenPair login(Authentication authentication) {
        Instant now = serverTime.nowInstant();

        String accessToken = jwtTokenProvider.generateToken(authentication);
        String refreshToken = random.nextString();

        RefreshToken refreshTokenDomain = RefreshToken.builder()
                .refreshToken(refreshToken)
                .subject(authentication.getName())
                .createdAt(now)
                .ttl(2_628_000L)
                .build();

        refreshTokenRepository.save(refreshTokenDomain);

        return JwtTokenPair.builder()
                .accessToken(STR."Bearer \{accessToken}")
                .refreshToken(refreshToken)
                .build();
    }
}
