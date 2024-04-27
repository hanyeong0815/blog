package com.self.blog.security.jwt.exception;

import com.self.blog.security.utils.SetResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final SetResponse setResponse;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        JwtException jwtException = JwtErrorCode.DEFAULT.defaultException();
        setResponse.setErrorResponse(response, jwtException);
    }
}
