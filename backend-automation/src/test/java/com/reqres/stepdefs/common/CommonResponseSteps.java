package com.reqres.stepdefs.common;

import com.reqres.context.ScenarioContext;

import io.cucumber.java.en.Then;

public class CommonResponseSteps {

    private final ScenarioContext context;

    public CommonResponseSteps(ScenarioContext context) {
        this.context = context;
    }

    @Then("the response status code should be {int}")
    public void responseStatusCodeShouldBe(int statusCode) {
        context.getResponse()
               .then()
               .statusCode(statusCode);
    }

}
