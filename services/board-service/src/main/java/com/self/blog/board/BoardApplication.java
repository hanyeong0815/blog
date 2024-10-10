package com.self.blog.board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import static com.self.blog.common.support.ServerConstants.BASE_PACKAGE;

@SpringBootApplication(scanBasePackages = BASE_PACKAGE)
@ConfigurationPropertiesScan(basePackages = BASE_PACKAGE)
@EnableScheduling
@EnableAsync
public class BoardApplication {
    public static void main(String[] args) {
        SpringApplication.run(BoardApplication.class, args);
    }
}
