package com.self.blog.member.application.authentication.utils.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.self.blog.common.config.CustomLocalDatetimeSerializers;
import com.self.blog.common.support.exception.CustomException;
import com.self.blog.common.support.exception.ResponseError;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class ExceptionHandlerFilter extends OncePerRequestFilter {
    private final CustomLocalDatetimeSerializers customLocalDatetimeSerializers;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (CustomException ex) {
            setErrorResponse(response, ex);
        }
    }

    private void setErrorResponse(HttpServletResponse response, CustomException customException) {
        SimpleModule simpleModule = new SimpleModule()
                .addSerializer(
                        LocalDateTime.class,
                        customLocalDatetimeSerializers
                );

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(simpleModule);

        response.setStatus(customException.getErrorCode().defaultHttpStatus().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("utf-8");

        ResponseError errorResponse = ResponseError.of(customException);

        try {
            response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
