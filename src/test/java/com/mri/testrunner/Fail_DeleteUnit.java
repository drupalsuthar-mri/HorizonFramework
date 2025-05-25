package com.mri.testrunner;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "C:/Users/Khushali.Mehta/Desktop/HorizonFramework/src/test/resources/features/Fail_DeleteUnit.feature", // path to your feature files
        glue = {"com.mri.stepdefinitions","com.mri.hooks"},
        plugin = {"pretty", "json:target/json-report/cucumber.json","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
)
public class Fail_DeleteUnit extends AbstractTestNGCucumberTests {

}
