package com.cucumber.options;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/com/features",
        glue = {"com.stepDefinitons"},
        plugin = "json:target/jsonReports/cucumber-report.json")
public class TestRunner {
}
