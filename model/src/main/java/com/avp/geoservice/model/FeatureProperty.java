package com.avp.geoservice.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FeatureProperty {
    private String NUTS_ID;
    private Integer STAT_LEVL_;
    private Double SHAPE_AREA;
    private Double SHAPE_LEN;
}
