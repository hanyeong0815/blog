package com.self.blog.email.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;

@ConfigurationProperties("app.url.notification")
@ConfigurationPropertiesBinding
public record NotificationUrlProperties(String notiUrl) {
    public NotificationUrlProperties {
        if (notiUrl == null || notiUrl.isEmpty()) {
            notiUrl = "http://13.125.32.218:8000";
        }
    }
}
