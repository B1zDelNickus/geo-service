package com.avp.geoservice.web;

import com.avp.geoservice.model.Feature;
import com.avp.geoservice.service.FeatureService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class FeatureController {

    private final FeatureService findLocationService;

    @GetMapping("search/data")
    public List<Feature> searchData(
            @RequestParam Double xMin,
            @RequestParam Double xMax,
            @RequestParam Double yMin,
            @RequestParam Double yMax
    ) {
        return findLocationService.findByCoords(xMin, xMax, yMin, yMax);
    }

}
