package com.opus.assessment.integration;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/com/opus/assessment/integration/resources")
public class CucumberIntegrationTest {
}
