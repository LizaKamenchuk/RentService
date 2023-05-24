package org.kamenchuk.service.impl.integration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.wait.strategy.LogMessageWaitStrategy;
import org.testcontainers.junit.jupiter.Container;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@SpringBootTest
public class PostgresContainer {
    @Container
    public static PostgreSQLContainer container = new PostgreSQLContainer("postgres:15.3")
            .withDatabaseName("db")
            .withUsername("test_user")
            .withPassword("test_password");

    @BeforeAll
    public static void setup() {
        container.setWaitStrategy(
                new LogMessageWaitStrategy()
                        .withRegEx(".*database system is ready to accept connections.*\\s")
                        .withTimes(1)
                        .withStartupTimeout(Duration.of(60, ChronoUnit.SECONDS))
        );
        container.start();
    }

    @DynamicPropertySource
    static void postgresqlProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.password", container::getPassword);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.driver-class-name", container::getDriverClassName);
    }

    @AfterAll
    public static void afterAll() {
        container.stop();
    }
}
