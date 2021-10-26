package com.avp.geoservice.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Builder
@Data
//@Document(collection = "geo_place_data")
public class GeoPlaceData {

    @Id
    private String _id;

    private List<Feature> features;

}
