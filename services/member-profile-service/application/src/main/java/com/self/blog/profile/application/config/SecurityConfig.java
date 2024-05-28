package com.self.blog.profile.application.config;

import com.self.blog.security.config.DefaultSecurityConfig;
import com.self.blog.security.jwt.JwtTokenProvider;
import com.self.blog.security.utils.SetResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig extends DefaultSecurityConfig {

    protected SecurityConfig(JwtTokenProvider jwtTokenProvider, SetResponse setResponse) {
        super(jwtTokenProvider, setResponse);
    }

    @Bean
    @Override
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return super.securityFilterChain(http);
    }
}
