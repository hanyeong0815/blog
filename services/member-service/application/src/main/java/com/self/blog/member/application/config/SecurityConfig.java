package com.self.blog.member.application.config;

import com.self.blog.member.application.authentication.CustomAuthenticationManager;
import com.self.blog.member.application.authentication.UserAuthenticationFilter;
import com.self.blog.security.config.DefaultSecurityConfig;
import com.self.blog.security.jwt.JwtTokenProvider;
import com.self.blog.security.utils.SetResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig extends DefaultSecurityConfig {
    private final UserAuthenticationFilter userAuthenticationFilter;
    public SecurityConfig(JwtTokenProvider jwtTokenProvider, CustomAuthenticationManager customAuthenticationManager, SetResponse setResponse) {
        super(jwtTokenProvider, setResponse);
        AuthenticationManager providerManager = new ProviderManager(customAuthenticationManager);
        userAuthenticationFilter = new UserAuthenticationFilter(providerManager);
    }

    @Bean
    @Override
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return super.securityFilterChain(http.addFilterAt(userAuthenticationFilter, UsernamePasswordAuthenticationFilter.class));
    }
}
