package com.avp.geoservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(
        basePackages = {
                "com.avp.geoservice.service",
                "com.avp.geoservice.service.*",
        }
)
@RequiredArgsConstructor
public class ServiceConfiguration {

}
