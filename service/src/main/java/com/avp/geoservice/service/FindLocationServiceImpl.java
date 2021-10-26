package com.avp.geoservice.service;

import com.avp.geoservice.model.Feature;
import com.avp.geoservice.repository.mongo.FeatureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.geo.Point;
import org.springframework.data.geo.Polygon;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FindLocationServiceImpl implements FindLocationService {

    private FeatureRepository locationRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Feature> findByCoords(Double xMin, Double xMax, Double yMin, Double yMax) {
        return locationRepository.findAllByGeometryWithin(
                new Polygon(
                        new Point(xMin, yMax),
                        new Point(xMax, yMax),
                        new Point(xMax, yMin),
                        new Point(xMin, yMin)
                )
        );
    }
}
