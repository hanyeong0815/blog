package com.self.blog.member.application.authentication;

import com.self.blog.member.application.aop.MemberLogin;
import com.self.blog.member.application.exception.MemberErrorCode;
import com.self.blog.member.application.authentication.utils.CommonAuthenticationToken;
import com.self.blog.member.application.authentication.utils.UserAuthenticationToken;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import static com.self.blog.common.utils.exception.Preconditions.validate;


@Component
@RequiredArgsConstructor
public class CustomAuthenticationManager implements AuthenticationProvider {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder pwEncoder;

    @MemberLogin // 로그인 시 필요한 로그 등의 작업 AOP 연결 annotation
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserDetails user = userDetailsService.loadUserByUsername(authentication.getName()); // member table에서 회원 조회

        // 유저가 없을 시 throw exception
        validate(
                user == null,
                MemberErrorCode.NO_SUCH_USER
        );

        // password 대조
        boolean isAuthenticated = pwEncoder.matches(
                (String) authentication.getCredentials(),
                user.getPassword()
        );

        // password 대조 실패 시 throw exception
        validate(
                !isAuthenticated,
                MemberErrorCode.INVALID_USERNAME_OR_PASSWORD
        );

        // 인증이 완료된 authentication token 생성
        authentication = CommonAuthenticationToken
                .authenticated(
                        UserAuthenticationToken.class,
                        authentication.getName(),
                        user,
                        user.getAuthorities()
                );
        ((CommonAuthenticationToken)authentication).eraseCredentials();
        return authentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UserAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
