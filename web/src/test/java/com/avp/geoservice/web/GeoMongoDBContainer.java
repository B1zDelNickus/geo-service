package com.avp.geoservice.web;

import org.testcontainers.containers.MongoDBContainer;

import java.util.Arrays;

public class GeoMongoDBContainer extends MongoDBContainer {
    public static final String MONGODB_IP_VAR_NAME = "MONGODB_IP_VAR_NAME";
    public static final String MONGODB_PORT_VAR_NAME = "MONGODB_PORT_VAR_NAME";

    private static final String IMAGE_VERSION = "mongo:4.2.10-bionic";
    private static GeoMongoDBContainer container;
    private static final int MONGO_DB_PORT = 27017;

    private GeoMongoDBContainer() {
        super(IMAGE_VERSION);
    }

    public static GeoMongoDBContainer getInstance() {
        if (container == null) {
            container = new GeoMongoDBContainer();

        }
        return container;
    }

    @Override
    public void start() {
        super.setExposedPorts(Arrays.asList(MONGO_DB_PORT));
        super.start();

        String ip = String.valueOf(container.getContainerIpAddress());
        String externalMongoDBPort = String.valueOf(container.getMappedPort(MONGO_DB_PORT));

        System.setProperty(MONGODB_IP_VAR_NAME, ip);
        System.setProperty(MONGODB_PORT_VAR_NAME, externalMongoDBPort);
    }

    @Override
    public void stop() {
        //do nothing, JVM handles shut down
    }

    public static Integer getMongoDBPort() {
        Integer mongoPort = Integer.valueOf(System.getProperty(MONGODB_PORT_VAR_NAME));

        return mongoPort;
    }

    public static String getContainerIp() {
        String containerIp = System.getProperty(MONGODB_IP_VAR_NAME);

        return containerIp;
    }
}
