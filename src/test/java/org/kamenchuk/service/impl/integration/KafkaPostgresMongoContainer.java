package org.kamenchuk.service.impl.integration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;

public class KafkaPostgresMongoContainer {
    @Container
    public static KafkaContainer containerKafka = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:latest"))
            .withEmbeddedZookeeper();
    @Container
    public static PostgreSQLContainer containerPostgres = new PostgreSQLContainer("postgres:15.3")
            .withDatabaseName("db")
            .withUsername("test_user")
            .withPassword("test_password");
    @Container
    public static MongoDBContainer containerMongo = new MongoDBContainer("mongo:latest");

    @Container
   public static GenericContainer appContainer = new GenericContainer<>()
            .dependsOn(containerKafka)
            .dependsOn(containerPostgres)
            .dependsOn(containerMongo);

    @BeforeAll
    public static void setup() {
        appContainer.start();
//        containerMongo.start();
//        containerKafka.start();
//        containerPostgres.start();
    }

    @DynamicPropertySource
    static void kafkaProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.kafka.bootstrap-servers", containerKafka::getBootstrapServers);
        registry.add("spring.kafka.consumer.bootstrap-servers", containerKafka::getBootstrapServers);
        registry.add("spring.kafka.producer.bootstrap-servers", containerKafka::getBootstrapServers);
    }

    @DynamicPropertySource
    static void postgresqlProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", containerPostgres::getJdbcUrl);
        registry.add("spring.datasource.password", containerPostgres::getPassword);
        registry.add("spring.datasource.username", containerPostgres::getUsername);
        registry.add("spring.datasource.driver-class-name", containerPostgres::getDriverClassName);
    }

    @DynamicPropertySource
    static void mongoProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.port", () -> containerMongo.getFirstMappedPort());
    }

    @AfterAll
    public static void afterAll() {
        appContainer.stop();
//        containerPostgres.stop();
//        containerKafka.stop();
//        containerMongo.stop();
    }
}
