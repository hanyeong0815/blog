package com.self.blog.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

import static com.self.blog.common.support.ServerConstants.BASE_PACKAGE;

@Configuration
@RequiredArgsConstructor
@ConfigurationPropertiesScan(basePackageClasses = RedisProperties.class)
@EnableRedisRepositories(basePackages = BASE_PACKAGE)
@EntityScan(basePackages = BASE_PACKAGE)
public class RedisConfig {
    private final RedisProperties redisProperties;

    @Bean
    public RedisConnectionFactory redisConnectionFactory(@Value("${spring.profiles.active}") String activeProfile) {
        RedisConfiguration redisConfiguration;

        if ("local".equalsIgnoreCase(activeProfile)) {
            // Standalone:
            redisConfiguration = new RedisStandaloneConfiguration(
                    redisProperties.getHost(),
                    redisProperties.getPort()
            );
        } else {
            // Cluster: (Node가 1개여도 클러스터가 될 수 있음. 최소 3개일 때 생성 -> 2개 지우면 1개)
            redisConfiguration = new RedisClusterConfiguration(
                    redisProperties.getCluster().getNodes()
            );
        }

        return new LettuceConnectionFactory(redisConfiguration);
    }
}
