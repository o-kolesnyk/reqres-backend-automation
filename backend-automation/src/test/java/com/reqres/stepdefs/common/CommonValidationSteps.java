package com.reqres.stepdefs.common;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;
import java.time.format.DateTimeParseException;

import com.reqres.context.ScenarioContext;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.restassured.path.json.JsonPath;

public class CommonValidationSteps {

    private final ScenarioContext context;

    public CommonValidationSteps(ScenarioContext context) {
        this.context = context;
    }

    @Then("the response field {string} should be a non-empty string")
    public void fieldShouldBeANonEmptyString(String fieldPath) {
        Object value = context.getResponse().jsonPath().get(fieldPath);

        assertThat(value)
            .as("Field '%s' should exist and be a string", fieldPath)
            .isNotNull()
            .isInstanceOf(String.class);

        assertThat((String) value)
            .as("Field '%s' should not be empty", fieldPath)
            .isNotBlank();
    }

    @Then("the response field {string} should be a valid timestamp")
    public void fieldShouldBeValidTimestamp(String fieldPath) {
        String value = context.getResponse().jsonPath().getString(fieldPath);

        assertThat(value)
            .as("Field '%s' should not be null or empty", fieldPath)
            .isNotNull()
            .isNotBlank();

        try {
            Instant.parse(value);
        } catch (DateTimeParseException e) {
            throw new AssertionError(
                String.format("Field '%s' value '%s' is not a valid ISO-8601 timestamp", fieldPath, value), e);
        }
    }

    @Then("the response should contain the following values:")
    public void responseShouldContainValues(DataTable dataTable) {
        JsonPath json = context.getResponse().jsonPath();

        dataTable.asMap(String.class, String.class)
            .forEach((key, expectedValue) -> {
                Object actual = json.get(key);
                assertThat(actual.toString())
                    .as("Expected value for key %s to be %s", key, expectedValue)
                    .isEqualTo(expectedValue);
            });
    }

    @Then("the response should contain the following keys:")
    public void responseShouldContainKeys(DataTable dataTable) {
        JsonPath json = context.getResponse().jsonPath();

        for (String path : dataTable.asList(String.class)) {
            assertThat(json.get(path).toString())
                .as("Expected key %s to be present", path)
                .isNotNull();
        }
    }
}
