package com.mri.stepdefinitions;

import com.mri.util.TestContext;
import io.cucumber.java.en.*;

public class LoginSteps {
    private final TestContext context;
    // DI through constructor
    public LoginSteps(TestContext context) {
        this.context = context;
    }

    @Given("User open the application")
    public void user_open_the_application() {
       String url= context.getProp().getProperty("url").trim();
        System.out.println("Navigating to URL: " + url);
        context.getPage().navigate(url);
    }

    @When("User Click on the MRI SaaS Okta preview Button")
    public void user_click_on_the_mri_saas_okta_preview_button() {
        context.getLoginPage().clickOktaSignInButton();
    }

    @Then("User should be redirected to the login page")
    public void user_should_be_redirected_to_the_login_page() {

    }

    @And("User enters email in email field")
    public void userEntersEmailInEmailField() {
       context.getLoginPage().LoginEmail(context.getProp().getProperty("email"));
    }

    @And("User enters password in password field")
    public void userEntersPasswordInPasswordField() {
        context.getLoginPage().LoginPassword(context.getProp().getProperty("password"));
    }

    @Then("User click on the Login Button")
    public void user_click_on_the_login_button() {

        context.getLoginPage().LoginButton();
    }
}
