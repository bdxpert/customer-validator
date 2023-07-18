package com.opus.assessment.bdd;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/com/opus/assessment/bdd/resources")
public class CucumberIntegrationTest {
}
