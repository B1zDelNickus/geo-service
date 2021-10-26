package com.avp.geoservice.service;

import com.avp.geoservice.model.Feature;
import com.avp.geoservice.repository.service.FeatureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FindLocationServiceImpl implements FindLocationService {

    private final FeatureRepository locationRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Feature> findByCoords(Double xMin, Double xMax, Double yMin, Double yMax) {
        return locationRepository.findAllByGeometryWithin(xMin, xMax, yMin, yMax);
    }

}
