package com.avp.geoservice.service;

import com.avp.geoservice.model.Feature;
import com.avp.geoservice.repository.mongo.FeatureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FindLocationServiceImpl implements FindLocationService {

    private final FeatureRepository locationRepository;
    private final MongoTemplate template;

    @Override
    @Transactional(readOnly = true)
    public List<Feature> findByCoords(Double xMin, Double xMax, Double yMin, Double yMax) {

        Point p1 = new Point(xMin, yMax);
        Point p2 = new Point(xMax, yMax);
        Point p3 = new Point(xMax, yMin);
        Point p4 = new Point(xMin, yMin);

        Query query = new Query()
                .addCriteria(Criteria.where("geometry")
                        .intersects(new GeoJsonPolygon(p1, p2, p3, p4, p1))
                );
        return template.find(query, Feature.class);

        /*return locationRepository.findAllByGeometryWithin(
                new GeoJsonPolygon(p1, p2, p3, p4, p1)
        );*/

    }

    @Override
    public List<Feature> findByPoint(Double lat, Double lng) {
        return null;
    }
}
