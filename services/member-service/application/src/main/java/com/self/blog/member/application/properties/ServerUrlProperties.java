package com.self.blog.member.application.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;


public record ServerUrlProperties(String serverUrl) {
    @ConfigurationProperties("app.url.profile")
    @ConfigurationPropertiesBinding
    public record ProfileServerProperties(String serverUrl) {
        public ProfileServerProperties {
            if (serverUrl == null || serverUrl.isEmpty()) {
                serverUrl = "http://localhost:9090";
            }
        }
    }
}
