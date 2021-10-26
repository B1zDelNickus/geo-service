package com.avp.geoservice.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

@Builder
@Data
public class FeatureProperty {
    @Field("NUTS_ID")
    private String nutsId;
    @Field("STAT_LEVL_")
    private Integer statLevel;
    @Field("SHAPE_AREA")
    private Double shapeArea;
    @Field("SHAPE_LEN")
    private Double shapeLength;
}
