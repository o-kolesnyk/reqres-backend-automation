package com.reqres.stepdefs.users;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.When;

import java.util.Map;

import com.reqres.client.UserApiClient;
import com.reqres.context.ScenarioContext;

public class UserCreationSteps {

  private final ScenarioContext context;
  private final UserApiClient userApiClient;

  public UserCreationSteps(ScenarioContext context, UserApiClient userApiClient) {
    this.context = context;
    this.userApiClient = userApiClient;
  }

  @When("I create a user with the following details:")
  public void iCreateUserWithDetails(DataTable dataTable) {
    Map<String, Object> userData = dataTable.asMap(String.class, Object.class);
    context.setResponse(userApiClient.createUser(userData));
  }

  @When("I create a user with invalid JSON body")
  public void iCreateUserWithInvalidJsonBody() {
    String invalidJson = "{ \"email\": \"test@test.com\", ";
    context.setResponse(userApiClient.createUser(invalidJson));
  }
}