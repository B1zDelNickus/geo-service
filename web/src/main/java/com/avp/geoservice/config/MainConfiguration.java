package com.avp.geoservice.config;

import com.avp.geoservice.model.Feature;
import com.avp.geoservice.repository.config.MongoConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;

import javax.annotation.PostConstruct;

@Configuration
@Import({
        MongoConfiguration.class,
        ServiceConfiguration.class,
        WebConfiguration.class
})
@RequiredArgsConstructor
public class MainConfiguration {

    private final MongoTemplate template;

    @PostConstruct
    public void init() {
        createIfNotExists(Feature.class);
    }

    private <T> void createIfNotExists(Class<T> clz) {
        if (!template.collectionExists(clz)) {
            template.createCollection(clz);
        }
    }

}
