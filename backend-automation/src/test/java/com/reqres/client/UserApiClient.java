package com.reqres.client;

import java.util.Map;

import io.restassured.response.Response;

public class UserApiClient extends BaseApiClient {

    private static final String USER_ENDPOINT = "/users";

    public Response getUserById(int id) {
        return baseRequest()
            .when()
            .log().all()
            .get(USER_ENDPOINT + "/" + id);
    }

    public Response createUser(Map<String, Object> userData) {
        return baseRequest()
            .body(userData)
            .when()
            .post(USER_ENDPOINT)
            .then()
            .extract()
            .response();
    }

    public Response createUser(String userData) {
        return baseRequest()
            .body(userData)
            .when()
            .post(USER_ENDPOINT)
            .then()
            .extract()
            .response();
    }
}
