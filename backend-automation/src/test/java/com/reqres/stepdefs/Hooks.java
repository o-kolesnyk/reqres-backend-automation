package com.reqres.stepdefs;

import com.reqres.context.ScenarioContext;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

  private final ScenarioContext context;

  public Hooks(ScenarioContext context) {
    this.context = context;
  }

  @Before
  public void beforeScenario(Scenario scenario) {
    context.clear();
  }

  @After
  public void afterScenario(Scenario scenario) {
    if (scenario.isFailed() && context.getResponse() != null) {
      scenario.attach(
          context.getResponse().asPrettyString(),
          "application/json",
          "Response body"
      );
    }
  }
}
