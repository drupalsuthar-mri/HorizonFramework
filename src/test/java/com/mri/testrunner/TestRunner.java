package com.mri.testrunner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

import java.util.Objects;

@CucumberOptions(
        features = {"C:/Users/Drupal.Suthar/Downloads/custom " +
                "framework/HorizonFramework/src/test/resources/features/SignIn.feature"},
        glue = {"com.mri.stepdefinitions", "com.mri.hooks"},
        plugin = {"pretty"}
)
public class TestRunner extends AbstractTestNGCucumberTests {

}