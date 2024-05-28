package com.self.blog.member.application.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.self.blog.member.application.authentication.utils.CommonAuthenticationToken;
import com.self.blog.member.application.authentication.utils.UserAuthenticationToken;
import com.self.blog.member.domain.Member;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class UserAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    public UserAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
        setRequiresAuthenticationRequestMatcher(
                new OrRequestMatcher(
                        new AntPathRequestMatcher(
                                "/member/login"
                        )
                )
        );

        setAuthenticationSuccessHandler(((request, response, authentication) -> {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
            }
        }));
    }

    // 로그인 인증 security 관련 AOP
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username;
        String password;

        try {
            String requestBody = StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8);
            ObjectMapper objectMapper = new ObjectMapper();
            Member loginRequest = objectMapper.readValue(requestBody, Member.class);
            username = loginRequest.username.trim();
            password = (loginRequest.password != null) ? loginRequest.password : "";
        } catch (Exception e) {
            throw new BadCredentialsException("invalid credential request");
        }

        Authentication authentication;

        try {
            // 로그인 인증을 위한 토큰 생성
            authentication = CommonAuthenticationToken.unauthenticated(
                    UserAuthenticationToken.class, username, password
            );
        } catch (Exception e) {
            throw new AuthenticationServiceException("occurred a problem while creating token");
        }

        return this.getAuthenticationManager().authenticate(authentication); // 로그인 인증
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authResult);
        SecurityContextHolder.setContext(context);
        chain.doFilter(request, response);
    }
}
