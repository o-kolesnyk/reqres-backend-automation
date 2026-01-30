package com.reqres.config;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;

@Getter
public final class RestAssuredConfig {

    private final RequestSpecification requestSpecification;

    private RestAssuredConfig() {
        requestSpecification =
            new RequestSpecBuilder()
                .setBaseUri(PropertiesConfig.getInstance().getBaseUrl())
                .setBasePath(PropertiesConfig.getInstance().getBasePath())
                .addHeader("x-api-key", PropertiesConfig.getInstance().getApiKey())
                .setContentType(ContentType.JSON)
                .build();
    }

    /**
     * Thread-safe lazy initialization via Holder
     */
    public static RestAssuredConfig getInstance() {
        return Holder.INSTANCE;
    }

    private static final class Holder {
        static final RestAssuredConfig INSTANCE = new RestAssuredConfig();
    }
}
