package com.reqres.stepdefs.users;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import com.reqres.context.ScenarioContext;

import io.cucumber.java.en.Then;

public class UserValidationSteps {

    private final ScenarioContext context;

    public UserValidationSteps(ScenarioContext context) {
        this.context = context;
    }

    @Then("the response should include service metadata")
    public void responseShouldIncludeServiceMetadata() {
        Object meta = context.getResponse().jsonPath().get("_meta");

        assertThat(meta)
            .as("Response should contain '_meta' object with service metadata")
            .isNotNull()
            .isInstanceOf(Map.class);
    }
}
