package com.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/ecommerce",
        glue = {"com.stepDefinitions.ecommerce",
                "com.stepDefinitions.common",
                "com.hooks"},
        plugin = "json:target/jsonReports/cucumber-report.json")
public class EcommerceRunner {
}
