package com.self.music.common.properties.time;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;

@ConfigurationProperties("app.time")
@ConfigurationPropertiesBinding
public record ServerTimeProperties(String timeZone) {
    public ServerTimeProperties {
        if (timeZone == null || "".equals(timeZone)) {
            timeZone = "Asia/Seoul";
        }
    }
}
