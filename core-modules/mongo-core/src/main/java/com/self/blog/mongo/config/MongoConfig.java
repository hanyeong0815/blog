package com.self.blog.mongo.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.bson.UuidRepresentation;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Objects;

import static com.self.blog.common.support.ServerConstants.BASE_PACKAGE;

@Configuration
@EnableMongoRepositories(basePackages = BASE_PACKAGE)
@ConfigurationPropertiesScan(basePackageClasses = MongoProperties.class)
public class MongoConfig extends AbstractMongoClientConfiguration {
    private final String username;
    private final String password;
    private final String database;
    private final String authenticationDatabase;
    private final String host;
    private final Integer port;

    public MongoConfig(MongoProperties properties) {
        Objects.requireNonNull(properties.getUsername());
        Objects.requireNonNull(properties.getPassword());
        Objects.requireNonNull(properties.getDatabase());
        Objects.requireNonNull(properties.getAuthenticationDatabase());
        Objects.requireNonNull(properties.getHost());
        Objects.requireNonNull(properties.getPort());
        this.username = properties.getUsername();
        this.password = String.valueOf(properties.getPassword());
        this.database = properties.getDatabase();
        this.authenticationDatabase = properties.getAuthenticationDatabase();
        this.host = properties.getHost();
        this.port = properties.getPort();
    }

    @Override
    public MongoClient mongoClient() {
        ConnectionString connectionString = new ConnectionString(
                STR."mongodb://\{username}:\{password}@\{host}:\{port}/\{database}?authSource=\{authenticationDatabase}"
        );
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .uuidRepresentation(UuidRepresentation.STANDARD)
                .build();
        return MongoClients.create(mongoClientSettings);
    }

    @Override
    protected String getDatabaseName() {
        return database;
    }
}
