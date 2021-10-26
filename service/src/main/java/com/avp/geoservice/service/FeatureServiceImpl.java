package com.avp.geoservice.service;

import com.avp.geoservice.model.Feature;
import com.avp.geoservice.service.find.FindFeatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FeatureServiceImpl implements FeatureService {

    private final FindFeatureService findFeatureService;

    @Override
    public List<Feature> findByCoords(Double xMin, Double xMax, Double yMin, Double yMax) {
        return findFeatureService.findAllByGeometryWithin(xMin, xMax, yMin, yMax);
    }

}
