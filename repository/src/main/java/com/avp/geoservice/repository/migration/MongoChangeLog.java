package com.avp.geoservice.repository.migration;

import com.avp.geoservice.model.Feature;
import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.decorator.impl.MongockTemplate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ChangeLog
public class MongoChangeLog {

    private static final String LOG_TEMPLATE = "Make Mongo migration: {}";
    private static final String AUTHOR = "mongock";
    private static final String MIGRATION_001 = "changeWithTemplate_001";

    @ChangeSet(order = "001", id = MIGRATION_001, author = AUTHOR)
    public void changeWithTemplate(MongockTemplate template) {
        log.info(LOG_TEMPLATE, MIGRATION_001);
        createIfNotExists(template, Feature.class);
    }

    private <T> void createIfNotExists(MongockTemplate template, Class<T> clz) {
        if (!template.collectionExists(clz)) {
            template.createCollection(clz);
        }
    }

}
