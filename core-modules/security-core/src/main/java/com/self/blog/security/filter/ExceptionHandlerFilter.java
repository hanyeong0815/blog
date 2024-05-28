package com.self.blog.security.filter;

import com.self.blog.common.support.exception.CustomException;
import com.self.blog.security.utils.SetResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class ExceptionHandlerFilter extends OncePerRequestFilter {
    private final SetResponse setResponse;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (CustomException ex) {
            setResponse.setErrorResponse(response, ex);
        }
    }
}
