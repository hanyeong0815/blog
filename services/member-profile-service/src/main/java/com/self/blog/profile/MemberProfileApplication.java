package com.self.blog.profile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

import static com.self.blog.common.support.ServerConstants.BASE_PACKAGE;

@SpringBootApplication(
        scanBasePackages = BASE_PACKAGE,
        exclude = { UserDetailsServiceAutoConfiguration.class }
)
@ConfigurationPropertiesScan(basePackages = BASE_PACKAGE)
public class MemberProfileApplication {
    public static void main(String[] args) {
        SpringApplication.run(MemberProfileApplication.class, args);
    }
}
