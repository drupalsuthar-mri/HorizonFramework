package com.mri.stepdefinitions;

import com.mri.util.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SignInSteps {
    private final TestContext context;

    public SignInSteps(TestContext context) {
        this.context = context;
    }

    @Given("user is on Welcome page")
    public void userIsOnWelcomePage() {
        String url = context.getProp().getProperty("url").trim();
        System.out.println("URL: " + url);
        context.getPage().navigate(url);
        System.out.println("Step 1: User is on Welcome page");
    }

    @When("user clicks on MRI Okta button")
    public void userClicksOnMRIOktaButton() {
        context.getSigninPage().ClickOktaSignInBtn();
        System.out.println("Step 2: User clicks on MRI Okta button");
    }

    @And("user is forwarded to Okta login page")
    public void userIsForwardedToOktaLoginPage() {
        String signInPageTitle = context.getSigninPage().getSignInPageTitle();
        System.out.println("Step 3: User is forwarded to Okta login page with title: " + signInPageTitle);
    }

    @And("user enters email in the email address field")
    public void userEntersInTheEmailAddressField() {
        String emailId = context.getProp().getProperty("email").trim();
        context.getSigninPage().enterEmailInEmailField(emailId);
        System.out.println("Step 4: User enters " + emailId + " in the email address field");
    }

    @And("user enters password in the password field")
    public void userEntersInThePasswordField() {
        String password = context.getProp().getProperty("password").trim();
        context.getSigninPage().enterPasswordInPasswordField(password);
        System.out.println("Step 5: User enters " + password + " in the password field");
    }

    @And("user clicks on remember me checkbox")
    public void userClicksOnRememberMeCheckbox() {
        context.getSigninPage().clickRememberMeBtn();
        System.out.println("Step 6: User clicks on remember me checkbox");
    }

    @And("user clicks on sign in button")
    public void userClicksOnSignInButton() {
        context.getSigninPage().clickSignInButton();
        System.out.println("Step 7: User clicks on sign in button");
    }

    @Then("user is redirected to the MRI home page")
    public void userIsRedirectedToTheMRIHomePage() {
        context.getSigninPage().navigateToHomePage();
    }
}