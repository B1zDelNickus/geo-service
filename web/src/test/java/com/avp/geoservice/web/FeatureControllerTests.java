package com.avp.geoservice.web;

import com.avp.geoservice.model.Feature;
import com.avp.geoservice.repository.mongo.FeatureMongoRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@GeoServiceTest
public class FeatureControllerTests extends BaseIntegrationTest {

    @Autowired
    private FeatureMongoRepository repository;

    private final Feature f1 = Feature
            .builder()
            .id(1)
            .type("Polygon")
            .geometry(
                    new GeoJsonPolygon(
                            new Point(0, 5),
                            new Point(5, 5),
                            new Point(5, 0),
                            new Point(0, 0),
                            new Point(0, 5)
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
                            new Point(10, 10),
                            new Point(10, 11)
                    )
            )
            .build();

    @BeforeEach
    public void setup() {
        repository.save(f1);
        repository.save(f2);
    }

    @AfterEach
    public void clean() {
        repository.deleteAll();
    }

    @Test
    public void test() throws Exception {

        final String xMin = "2.0";
        final String xMax = "3.0";
        final String yMin = "2.0";
        final String yMax = "3.0";

        final String params = String.format("yMin=%s&yMax=%s&xMin=%s&xMax=%s", yMin, yMax, xMin, xMax);

        final String baseUrl = getEndpointUrl("/api/search/data?" + params);

        log.debug("Request path: {}", baseUrl);

        this.mockMvc.perform(get(baseUrl))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(1)))
                .andExpect(jsonPath("$.[0].id").value("1"))
                .andExpect(jsonPath("$.[0].type").value("Polygon"));

    }

}
