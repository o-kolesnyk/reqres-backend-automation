package com.reqres.context;

import io.restassured.response.Response;
import lombok.Data;

@Data
public class ScenarioContext {
  private Response response;

  public void clear() {
    this.response = null;
  }
}

