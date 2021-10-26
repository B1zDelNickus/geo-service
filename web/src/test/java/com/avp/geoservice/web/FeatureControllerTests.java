package com.avp.geoservice.web;

import com.avp.geoservice.model.Feature;
import com.avp.geoservice.model.GeoPlaceData;
import com.avp.geoservice.repository.mongo.FeatureRepository;
import com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.decorator.impl.MongockTemplate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.data.geo.Polygon;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@GeoServiceTest
public class FeatureControllerTests extends BaseIntegrationTest {

    @Autowired
    private FeatureRepository locationRepository;

    private final Feature f1 = Feature
            .builder()
            .id(1)
            .type("Polygon")
            .geometry(
                    new GeoJsonPolygon(
                            new Point(0, 5),
                            new Point(5, 5),
                            new Point(5, 0),
                            new Point(0, 0)
                    )
            )
            .build();

    private final Feature f2 = Feature
            .builder()
            .id(2)
            .type("Polygon")
            .geometry(
                    new GeoJsonPolygon(
                            new Point(10, 11),
                            new Point(11, 11),
                            new Point(11, 10),
                            new Point(10, 10)
                    )
            )
            .build();

    @BeforeEach
    public void setup() {
        locationRepository.save(f1);
        locationRepository.save(f2);
    }

    @AfterEach
    public void clean() {
        locationRepository.deleteAll();
    }

    @Test
    public void test() throws Exception {

        final String xMin = "2.0";
        final String xMax = "3.0";
        final String yMin = "2.0";
        final String yMax = "3.0";

        final String params = String.format("yMin=%s&yMax=%s&xMin=%s&xMax=%s", yMin, yMax, xMin, xMax);

        final String baseUrl = getEndpointUrl("/api/search/data?" + params);
        final URI uri = new URI(baseUrl);

        log.debug("Request path: {}", baseUrl);

        List<Feature> result = (List<Feature>) restTemplate.getForObject(uri, List.class);

        assertThat(result.size()).isEqualTo(1);

        Feature feature = result.get(0);

        assertThat(feature.getId()).isEqualTo(f1.getId());
        assertThat(feature.getType()).isEqualTo(f1.getType());
        assertThat(feature.getGeometry()).isEqualTo(f1.getGeometry());

    }

}
