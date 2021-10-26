package com.avp.geoservice.web;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.test.context.ActiveProfiles;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@ActiveProfiles(value = {"test", "unsecured"})
@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
public @interface GeoServiceTest {
}
