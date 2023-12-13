package com.colak.springreactivetutorial;

import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

public abstract class BaseITTest {

    @ServiceConnection
    @Container
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:11.19-bullseye");

    @DynamicPropertySource
    static void registerPgProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.r2dbc.url", () -> "r2dbc:postgresql://"
                                           + postgreSQLContainer.getHost() + ":" + postgreSQLContainer.getFirstMappedPort());
        registry.add("spring.r2dbc.username", () -> postgreSQLContainer.getUsername());
        registry.add("spring.r2dbc.password", () -> postgreSQLContainer.getPassword());
    }

}
