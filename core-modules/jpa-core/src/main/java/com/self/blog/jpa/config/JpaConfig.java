package com.self.blog.jpa.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import static com.self.blog.common.support.ServerConstants.BASE_PACKAGE;

@Configuration
@EnableJpaRepositories(basePackages = BASE_PACKAGE)
@EntityScan(basePackages = BASE_PACKAGE)
public class JpaConfig {
}
