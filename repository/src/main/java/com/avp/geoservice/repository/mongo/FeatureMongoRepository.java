package com.avp.geoservice.repository.mongo;

import com.avp.geoservice.model.Feature;
import org.springframework.data.repository.CrudRepository;

public interface FeatureMongoRepository extends CrudRepository<Feature, String> {
}
