package com.mri.testrunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "C:/Users/Khushali.Mehta/Desktop/HorizonFramework/src/test/resources/features/Accounting.feature", // path to your feature files
        glue = {"com.mri.stepdefinitions","com.mri.hooks"},
        plugin = {"pretty"}
)
public class AccountingRunner extends AbstractTestNGCucumberTests {

}
