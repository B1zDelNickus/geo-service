package com.avp.geoservice.service;

import com.avp.geoservice.model.Feature;

import java.util.List;

public interface FeatureService {

    List<Feature> findByCoords(Double xMin, Double xMax, Double yMin, Double yMax);

}
