package com.avp.geoservice.web;

import com.avp.geoservice.model.Feature;
import com.avp.geoservice.service.FeatureService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class FeatureController {

    private final FeatureService findLocationService;

    @GetMapping("search/data")
    public List<Feature> searchData(
            @DecimalMax("90.0") @DecimalMin("-90.0")
            @RequestParam Double xMin,
            @DecimalMax("90.0") @DecimalMin("-90.0")
            @RequestParam Double xMax,
            @DecimalMax("180.0") @DecimalMin("-180.0")
            @RequestParam Double yMin,
            @DecimalMax("180.0") @DecimalMin("-180.0")
            @RequestParam Double yMax
    ) {
        return findLocationService.findByCoords(xMin, xMax, yMin, yMax);
    }

}
