package com.mri.stepdefinitions;

import com.mri.util.TestContext;
import io.cucumber.java.en.Given;

public class CommonSteps {
    private final TestContext context;
    public CommonSteps(TestContext context) {
        this.context = context;
    }
    @Given("User is Login to the application")
    public void userIsLoginToTheApplication() {

        String url= context.getProp().getProperty("url").trim();
        context.getPage().navigate(url);
        context.getLoginPage().DoLogin(context.getProp().getProperty("email"),
                context.getProp().getProperty("password"));


    }
}
