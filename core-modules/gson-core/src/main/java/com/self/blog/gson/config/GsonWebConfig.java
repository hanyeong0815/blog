package com.self.blog.gson.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.self.blog.common.properties.time.ServerTimeProperties;
import com.self.blog.gson.config.GsonSerializers.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Configuration
@RequiredArgsConstructor
@ConfigurationPropertiesScan(basePackageClasses = ServerTimeProperties.class)
public class GsonWebConfig {
    @Bean
    public Gson gson(ServerTimeProperties serverTimeProperties) {
        return new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer())
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer())
                .registerTypeAdapter(OffsetDateTime.class, new OffsetDateTimeSerializer())
                .registerTypeAdapter(
                        OffsetDateTime.class,
                        new OffsetDateTimeDeserializer(serverTimeProperties.timeZone())
                )
                .registerTypeAdapter(Instant.class, new InstantSerializer())
                .registerTypeAdapter(Instant.class, new InstantDeserializer())
                .setPrettyPrinting()
                .create();
    }
}