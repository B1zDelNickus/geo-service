package com.avp.geoservice.repository.config;

import com.github.cloudyrock.spring.v5.EnableMongock;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.*;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableMongock
@EnableMongoRepositories({"com.avp.geoservice.repository.mongo"})
@Configuration
@ComponentScan({"com.avp.geoservice.repository.mongo"})
@EnableTransactionManagement
@RequiredArgsConstructor
@Profile("!test")
public class MongoConfiguration {

    @Bean
    @Primary
    public MongoTransactionManager transactionManager(MongoDatabaseFactory dbFactory) {
        return new MongoTransactionManager(dbFactory);
    }

}
