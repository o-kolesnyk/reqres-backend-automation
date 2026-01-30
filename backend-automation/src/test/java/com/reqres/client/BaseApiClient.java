package com.reqres.client;

import com.reqres.config.RestAssuredConfig;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public abstract class BaseApiClient {

  protected RequestSpecification baseRequest() {
    return RestAssured
    .given()
    .spec(RestAssuredConfig.getInstance().getRequestSpecification());
  }
  
}
