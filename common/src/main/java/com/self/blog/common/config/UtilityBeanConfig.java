package com.self.music.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Base64.Encoder;

@Configuration
public class UtilityBeanConfig {

    @Bean
    public SecureRandom secureRandom() throws NoSuchAlgorithmException {
        // TODO 배포 후 (특히 리눅스) 테스트 필수.
        return SecureRandom.getInstanceStrong();
    }

    @Bean
    public Encoder encoder() {
        return Base64.getUrlEncoder().withoutPadding();
    }
}
