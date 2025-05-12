package com.mri.testrunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/EditPropertyRecord.feature",
        glue = {"com.mri.stepdefinitions", "com.mri.hooks"},
        plugin = {"pretty","json:target/json-report/cucumber.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
)
public class editPropertyRecordTestRunner extends AbstractTestNGCucumberTests {
}