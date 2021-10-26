package com.avp.geoservice.web;

import com.avp.geoservice.config.MainConfiguration;
import com.avp.geoservice.config.ServiceConfiguration;
import com.avp.geoservice.config.WebConfiguration;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.*;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@ActiveProfiles("test")
@SpringBootTest(
        classes = {
                WebConfiguration.class,
                BaseIntegrationTest.MongoConfig.class
        },
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@Testcontainers
@AutoConfigureWebClient(registerRestTemplate = true)
public abstract class BaseIntegrationTest {

    @Container
    protected static final GeoMongoDBContainer mongoDBContainer = GeoMongoDBContainer.getInstance();

    @Autowired
    protected RestTemplate restTemplate;

    @LocalServerPort
    private int randomServerPort;

    @BeforeEach
    public void beforeEach() {

    }

    protected String getEndpointUrl(String endpoint) {
        return String.format("http://localhost:%d%s", randomServerPort, endpoint);
    }

    @Configuration
    @EnableMongoRepositories({"com.avp.geoservice.repository.mongo"})
    @ComponentScan({"com.avp.geoservice.repository.mongo"})
    @EnableTransactionManagement
    public static class MongoConfig extends AbstractMongoClientConfiguration {

        @Bean
        @Primary
        public MongoTransactionManager transactionManager(MongoDatabaseFactory dbFactory) {
            return new MongoTransactionManager(dbFactory);
        }

        @Override
        protected String getDatabaseName() {
            return "testdb";
        }

        @Bean
        @Primary
        public MongoClient mongoClient() {
            String connectionString = String.format("mongodb://%s:%s", mongoDBContainer.getContainerIp(),
                    mongoDBContainer.getMongoDBPort());
            MongoClientSettings settings = MongoClientSettings.builder()
                    .applyConnectionString(new ConnectionString(connectionString)).build();
            return MongoClients.create(settings);
        }

        @Bean
        @Primary
        public MongoTemplate mongoTemplate() {
            return new MongoTemplate(mongoClient(), "test");
        }

    }

    @Configuration
    @Import({
            FeatureController.class
    })
    public static class TestApp {

    }

}
