package com.self.blog.member.application.config;

import com.self.blog.member.application.authentication.CustomAuthenticationManager;
import com.self.blog.member.application.authentication.UserAuthenticationFilter;
import com.self.blog.member.application.authentication.jwt.JwtFilter;
import com.self.blog.member.application.authentication.jwt.JwtTokenProvider;
import com.self.blog.member.application.authentication.utils.filter.ExceptionHandlerFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final UserAuthenticationFilter authenticationFilter;
    private final JwtFilter jwtFilter;
    private final ExceptionHandlerFilter exceptionHandlerFilter;

    SecurityConfig(CustomAuthenticationManager userAuthenticationProvider, JwtTokenProvider jwtTokenProvider, ExceptionHandlerFilter exceptionHandlerFilter) {
        jwtFilter = new JwtFilter(jwtTokenProvider);
        AuthenticationManager providerManager = new ProviderManager(userAuthenticationProvider);
        authenticationFilter = new UserAuthenticationFilter(providerManager);
        this.exceptionHandlerFilter = exceptionHandlerFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(corsConfigurer -> corsConfigurer.configurationSource(corsConfigurationSource()))
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterAt(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterAt(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(exceptionHandlerFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    private CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        configuration.addAllowedOrigin("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
