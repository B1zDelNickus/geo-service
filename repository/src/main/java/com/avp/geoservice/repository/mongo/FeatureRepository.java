package com.avp.geoservice.repository.mongo;

import com.avp.geoservice.model.Feature;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Polygon;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FeatureRepository extends CrudRepository<Feature, String> {

    List<Feature> findAllByGeometryWithin(Polygon polygon);

    List<Feature> findAllByGeometryWithin(Circle shape);

}
