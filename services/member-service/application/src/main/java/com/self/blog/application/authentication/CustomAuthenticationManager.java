package com.self.blog.application.authentication;

import com.self.blog.application.aop.MemberLogin;
import com.self.blog.application.exception.MemberErrorCode;
import com.self.blog.application.authentication.utils.CommonAuthenticationToken;
import com.self.blog.application.authentication.utils.UserAuthenticationToken;
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

    @MemberLogin
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserDetails user = userDetailsService.loadUserByUsername(authentication.getName());

        validate(
                user == null,
                MemberErrorCode.NO_SUCH_USER
        );

        boolean isAuthenticated = pwEncoder.matches(
                (String) authentication.getCredentials(),
                user.getPassword()
        );

        validate(
                !isAuthenticated,
                MemberErrorCode.INVALID_USERNAME_OR_PASSWORD
        );

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
