package com.reqres.stepdefs.users;

import io.cucumber.java.en.When;

import com.reqres.client.UserApiClient;
import com.reqres.context.ScenarioContext;

public class UserRetrievalSteps {

    private final ScenarioContext context;
    private final UserApiClient userApiClient;

    public UserRetrievalSteps(ScenarioContext context, UserApiClient userApiClient) {
        this.userApiClient = userApiClient;
        this.context = context;
    }

    @When("I request a user with id {int}")
    public void iRequestUser(int id) {
        context.setResponse(userApiClient.getUserById(id));
    }
}
