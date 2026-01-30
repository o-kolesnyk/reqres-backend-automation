package com.reqres.runners;

import io.cucumber.junit.platform.engine.Constants;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME, value = "com.reqres.stepdefs")
@ConfigurationParameter(
    key = Constants.PLUGIN_PROPERTY_NAME,
    value = "pretty, summary, html:target/cucumber/cucumber.html, json:target/cucumber/cucumber.json"
)
public class RunCucumberTest {

  /**
   * When Surefire runs with multiple forks, each fork gets its own report dir so reports are not overwritten.
   * System property takes precedence over the annotation value in Cucumber.
   */
  static {
    String forkNumber = System.getProperty("surefire.fork.number");
    if (forkNumber != null && !forkNumber.isEmpty()) {
      String reportDir = "target/cucumber/fork-" + forkNumber;
      System.setProperty(
          Constants.PLUGIN_PROPERTY_NAME,
          "pretty, summary, html:" + reportDir + "/cucumber.html, json:" + reportDir + "/cucumber.json");
    }
  }
}
