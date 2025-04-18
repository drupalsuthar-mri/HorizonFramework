package com.mri.stepdefinitions;

import com.mri.pages.MenuName;
import com.mri.util.TestContext;
import io.cucumber.java.en.*;

import java.util.List;

public class LoginSteps {
    private final TestContext context;
    // DI through constructor
    public LoginSteps(TestContext context) {
        this.context = context;
        this.context.setMenuName(new MenuName(context.getPage()));
    }

    @Given("User open the application")
    public void UserOpenTheApplication() {
       String url= context.getProp().getProperty("url").trim();
        context.getPage().navigate(url);
    }

    @When("User click on the MRI SaaS Okta preview button")
    public void UserClickOnTheMriSaasOktaPreviewButton() {
        context.getLoginPage().clickOktaSignInButton();
    }

    @Then("User should be redirected to the login page")
    public void UserShouldBeRedirectedToTheLoginPage() {
        System.out.println("User should be redirected to the login page");
    }

    @And("User enters email in email field")
    public void UserEntersEmailInEmailField() {
       context.getLoginPage().LoginEmail(context.getProp().getProperty("email"));
    }

    @And("User enters password in password field")
    public void UserEntersPasswordInPasswordField() {
        context.getLoginPage().LoginPassword(context.getProp().getProperty("password"));
    }

    @Then("User click on the Login Button")
    public void UserClickOnTheLoginButton() {
        context.getLoginPage().LoginButton();
    }

    @Given("User should be redirected to the Home Page")
    public void userIsLoggedInSuccessfully() {
        System.out.println("User is logged in successfully");
    }

    @When("User click on the menu button")
    public void userClickOnTheMenuButton() {
        System.out.println("User button");
        context.getMenuName().clickMenuIcon();
        System.out.println("User clicked on the menu button");
    }

    @Then("User should see the menu options")
    public void userShouldSeeTheMenuOptions() {
        List<String> menuItems = context.getMenuName().getMenuItems();
        System.out.println("Menu Options:");
        for (String item : menuItems) {
            System.out.println("- " + item);
        }
    }

}
