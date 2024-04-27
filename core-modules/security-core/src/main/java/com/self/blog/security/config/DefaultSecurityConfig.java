package com.self.blog.security.config;

import com.self.blog.security.filter.ExceptionHandlerFilter;
import com.self.blog.security.jwt.JwtFilter;
import com.self.blog.security.jwt.JwtTokenProvider;
import com.self.blog.security.jwt.exception.JwtAccessDeniedHandler;
import com.self.blog.security.jwt.exception.JwtAuthenticationEntryPoint;
import com.self.blog.security.utils.SetResponse;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

public class DefaultSecurityConfig {
    private final JwtFilter jwtFilter;
    private final ExceptionHandlerFilter exceptionHandlerFilter;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    protected DefaultSecurityConfig(JwtTokenProvider jwtTokenProvider, SetResponse setResponse) {
        jwtFilter = new JwtFilter(jwtTokenProvider);
        exceptionHandlerFilter = new ExceptionHandlerFilter(setResponse);
        jwtAccessDeniedHandler = new JwtAccessDeniedHandler(setResponse);
        jwtAuthenticationEntryPoint = new JwtAuthenticationEntryPoint(setResponse);
    }

    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic(AbstractHttpConfigurer::disable)
                .headers(AbstractHttpConfigurer::disable)
                .cors(corsConfigurer -> corsConfigurer.configurationSource(corsConfigurationSource()))
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(exceptionHandlerFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(exceptionHandler ->
                        exceptionHandler
                                .accessDeniedHandler(jwtAccessDeniedHandler)
                                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                );

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
