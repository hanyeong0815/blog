package com.self.blog.member.application.service;

import com.self.blog.email.client.NotificationEmailPostClient;
import com.self.blog.email.data.NotificationEmail.DirectNotificationEmail;
import com.self.blog.member.application.aop.MemberSave;
import com.self.blog.member.application.aop.PasswordUpdate;
import com.self.blog.member.application.exception.MemberErrorCode;
import com.self.blog.member.application.repository.InvitationCodeRepository;
import com.self.blog.member.application.repository.MemberRepository;
import com.self.blog.member.application.repository.RefreshTokenRepository;
import com.self.blog.member.application.usecase.*;
import com.self.blog.member.application.usecase.data.JwtTokenPair;
import com.self.blog.common.utils.random.StrongStringRandom;
import com.self.blog.common.utils.time.ServerTime;
import com.self.blog.member.application.usecase.data.MemberNicknameForWebClient.MemberNickname;
import com.self.blog.member.domain.InvitationCode;
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
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Instant;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

import static com.self.blog.common.utils.exception.Preconditions.validate;

@Service
@RequiredArgsConstructor
public class MemberService
        implements
        MemberSignupUseCase,
        UserDetailsService,
        MemberLoginUseCase,
        PasswordUpdateUseCase,
        VerifyUsernameUseCase,
        GetNicknameUseCase,
        CreateInvitationCodeUseCase,
        CertifyInvitationCodeUseCase
{
    private final MemberRepository memberRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final InvitationCodeRepository invitationCodeRepository;

    private final JwtTokenProvider jwtTokenProvider;

    private final PasswordEncoder encoder;
    private final StrongStringRandom random;

    private final ServerTime serverTime;

    private final NotificationEmailPostClient notificationEmailPostClient;
    private final WebClient webClient = WebClient.builder().baseUrl(BOARD_URL).build();

    private static final String BOARD_URL = "http://localhost:8090";

    @Override
    @MemberSave // 회원가입 시 처리할 AOP 작업들과 연결을 위한 annotation
    public Member save(Member member) {
        // hashing
        String hashPassword = encoder.encode(member.getPassword());
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
                .username(member.getUsername())
                .password(member.getPassword())
                .roles(member.getRoles().stream().map(role -> role.value).toArray(String[]::new))
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

    // TODO 확인 후 문제 없을 시 redis에 저장하여 일정시간동안 nickname 선점 -> 현재 로직에 redis에서도 검색하도록 수정
    @Override
    public boolean verifyUsername(String username) {
        return !memberRepository.existsByUsername(username);
    }

    @Override
    public String getNicknameUseCase(String username) {
        String uri = STR."/profile/\{username}";
        return Objects.requireNonNull(webClient.get()
                        .uri(uri)
                        .retrieve()
                        .bodyToMono(MemberNickname.class)
                        .block())
                .nickname();
    }

    @Override
    public boolean createInvitationCode(String email) {
        Random seed = new Random(System.currentTimeMillis());
        String invitationCode = String.valueOf(seed.nextInt(999999));

        InvitationCode savedInvitationCode = invitationCodeRepository.save(
                InvitationCode.builder()
                        .invitationCode(invitationCode)
                        .email(email)
                        .isVerified(false)
                        .ttl(300_000L)
                        .build()
        );

        return Objects.requireNonNull(notificationEmailPostClient.sendEmail(
                DirectNotificationEmail.builder()
                        .title("blog 회원가입을 위한 인증 메일입니다.")
                        .content(STR. "인증메일: \{ savedInvitationCode.getInvitationCode() }" )
                        .useHtml(true)
                        .targets(new String[]{savedInvitationCode.getEmail()})
                        .build()
        ).block()).getStatusCode().is2xxSuccessful();
    }

    @Override
    public boolean certifyInvitationCode(String invitationCode, String email) {
        Optional<InvitationCode> invitationCodeOptional = invitationCodeRepository.findById(invitationCode);

        if (invitationCodeOptional.isEmpty()) return false;

        Optional<InvitationCode> filteringInvitationCode = invitationCodeOptional
                .filter(foundedInvitationCode -> foundedInvitationCode.getEmail().equals(email));

        if (filteringInvitationCode.isEmpty()) {
            return false;
        }

        invitationCodeRepository.deleteById(filteringInvitationCode.get().getInvitationCode());
        return true;
    }
}
