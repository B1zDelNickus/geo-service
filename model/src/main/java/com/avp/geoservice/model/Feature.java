package com.avp.geoservice.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Data
@Document(collection = "feature")
public class Feature {
    private Integer id;
    private String type;
    private FeatureProperty properties;
    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    private GeoJsonPolygon geometry;

}
