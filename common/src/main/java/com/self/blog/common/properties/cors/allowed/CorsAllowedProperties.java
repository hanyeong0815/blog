package com.self.music.common.properties.cors.allowed;

import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;

@ConfigurationPropertiesBinding
public record CorsAllowedProperties(
        String[] headers,
        String[] methods,
        String[] origins
) {
    public CorsAllowedProperties {
        if (origins == null) {
            origins = new String[] {};
        }
        if (headers == null || headers.length == 0) headers = new String[] {"*"};
        if (methods == null || methods.length == 0) methods = new String[] {"*"};
    }
}
