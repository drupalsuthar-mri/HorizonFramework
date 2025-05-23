package com.mri.stepdefinitions;

import com.mri.pages.HomePage;
import com.mri.pages.handler.PropertyHandler;
import com.mri.pages.handler.SideMenuHandler;
import com.mri.util.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class checkAllPagesSteps {
    private final TestContext context;
    private SideMenuHandler sideMenuHandler;
    private boolean allPagesAreWorking;

    public checkAllPagesSteps(TestContext context) {
        this.context = context;
    }

    @Given("the user has signed into the application")
    public void theUserHasSignedIntoTheApplication() {
        String email = context.getProp().getProperty("email").trim();
        System.out.println("email: "+email );
        String password = context.getProp().getProperty("password").trim();
        System.out.println("password: "+password );
        context.getSigninPage().doSignIn(email, password);
        System.out.println("background: the user has signed into the application");
    }

    @Given("the user has navigated to the home page")
    public void theUserHasNavigatedToTheHomePage() {
        HomePage homePage = context.getSigninPage().navigateToHomePage();
        context.setHomePage(homePage);
        context.getHomePage().getHomePageTitle();
        System.out.println("Step 1: User is on the Home page");
    }

    @When("the user navigates to each side menu")
    public void theUserNavigatesToEachSideMenu() {
        sideMenuHandler = context.getHomePage().getSideMenuHandler();
        context.getPage().pause();
        allPagesAreWorking = sideMenuHandler.checkAllPagesAreWorking();
        System.out.println("Step 2: User navigates to each side menu");
    }

    @Then("the user verifies the pages are working or not")
    public void theUserVerifiesThePagesAreWorkingOrNot() {
        System.out.println("allPagesAreWorking: " +allPagesAreWorking);
        System.out.println("Step 3: User verifies the pages are working or not");
    }
}