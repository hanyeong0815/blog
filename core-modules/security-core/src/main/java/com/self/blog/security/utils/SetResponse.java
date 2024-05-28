package com.self.blog.security.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.self.blog.common.config.CustomLocalDatetimeSerializers;
import com.self.blog.common.support.exception.CustomException;
import com.self.blog.common.support.exception.ResponseError;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class SetResponse {
    private final CustomLocalDatetimeSerializers customLocalDatetimeSerializers;

    public void setErrorResponse(HttpServletResponse response, CustomException customException) {
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
