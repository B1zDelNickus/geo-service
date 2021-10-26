package com.avp.geoservice.service.find;

import com.avp.geoservice.model.Feature;

import java.util.List;

public interface FindFeatureService {

    List<Feature> findAllByGeometryWithin(Double xMin, Double xMax, Double yMin, Double yMax);

}
