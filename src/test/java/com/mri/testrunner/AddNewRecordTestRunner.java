package com.mri.testrunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/AddNewRecord.feature",
        glue = {"com.mri.stepdefinitions", "com.mri.hooks"},
        plugin = {"pretty"}
)
public class AddNewRecordTestRunner extends AbstractTestNGCucumberTests {
}