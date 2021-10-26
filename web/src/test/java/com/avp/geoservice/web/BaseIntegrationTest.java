package com.avp.geoservice.web;

import com.avp.geoservice.config.ServiceConfiguration;
import com.avp.geoservice.config.WebConfiguration;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@ActiveProfiles("test")
@SpringBootTest(
        classes = {
                WebConfiguration.class,
                ServiceConfiguration.class,
                BaseIntegrationTest.MongoConfig.class
        },
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ExtendWith(SpringExtension.class)
@Testcontainers
@AutoConfigureWebClient(registerRestTemplate = true)
public abstract class BaseIntegrationTest {

    @Container
    protected static final GeoMongoDBContainer mongoDBContainer = GeoMongoDBContainer.getInstance();

    @Autowired
    protected RestTemplate restTemplate;

    protected MockMvc mockMvc;

    @LocalServerPort
    private int randomServerPort;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void beforeEach() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
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

}
