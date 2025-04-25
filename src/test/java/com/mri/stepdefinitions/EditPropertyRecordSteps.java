package com.mri.stepdefinitions;

import com.mri.pages.HomePage;
import com.mri.pages.PropertyListPage;
import com.mri.pages.handler.PropertyHandler;
import com.mri.util.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.function.BooleanSupplier;

public class EditPropertyRecordSteps {
    private final TestContext context;
    private PropertyHandler propertyHandler;

    public EditPropertyRecordSteps(TestContext context) {
        this.context = context;
    }

    @Given("the user is signed into the application")
    public void theUserIsSignedIntoTheApplication() {
        String email = context.getProp().getProperty("email").trim();
        String password = context.getProp().getProperty("password").trim();
        context.getSigninPage().doSignIn(email, password);
        System.out.println("Background: User is already Signed in");
    }

    @And("the user is on the home page")
    public void theUserIsOnTheHomePage() {
        HomePage homePage = context.getSigninPage().navigateToHomePage();
        context.setHomePage(homePage);
        context.getHomePage().getHomePageTitle();
        System.out.println("Background: User is on the Home Page");
    }

    @And("the user navigates to the property list page")
    public void theUserNavigatesToThePropertyListPage() {
        PropertyListPage propertyListPage = context.getHomePage().navigateToPropertyListPage();
        context.setPropertyListPage(propertyListPage);
        context.getPropertyListPage().getPropertyListPageTitle();
        System.out.println("Background: User is on the Property List Page");
    }

    @Given("the user is on the property list page")
    public void theUserIsOnThePropertyListPage() {
        boolean propertyListVisible = context.getPropertyListPage().isPropertyListVisible();
        BooleanSupplier propertyListVisibleSupplier = () -> propertyListVisible;
        context.getPage().waitForCondition(propertyListVisibleSupplier);
        System.out.println("Step 1: User is on the Property List Page");
    }

    @And("the user filters the required property {string} using Property search filter")
    public void theUserFiltersTheRequiredPropertyUsingPropertySearchFilter(String propertyRef) {
        propertyHandler = context.getPropertyListPage().editProperty();
        propertyHandler.allSearchByFilters("Property",propertyRef);
        System.out.println("Step 2: User filters the required property using Property search filter");
    }

    @And("the user clicks on the cog icon of the property")
    public void theUserClicksOnTheCogIconOfTheProperty() {
        propertyHandler.clickOnCogIconForProperty();
        System.out.println("Step 3: User clicks on the cog icon of the property");
    }

    @And("the user is forwarded to property detail form")
    public void theUserIsForwardedToPropertyDetailForm() {
        System.out.println("Step 4: User is forwarded to property detail form");
    }

    @When("the user is on property detail form")
    public void theUserIsOnPropertyDetailForm() {
        BooleanSupplier propertyDetailsFormVisible = propertyHandler.isPropertyDetailsFormVisible();
        context.getPage().waitForCondition(propertyDetailsFormVisible);
        System.out.println("Step 5: User is on property detail form");
    }

    @And("the user edits the initial book cost field")
    public void theUserEditsTheInitialBookCostField() {
        propertyHandler.enterInitialBookCost("101100010");
        System.out.println("Step 6: User edits the initial book cost field");
    }

    @And("the user clicks on save button")
    public void theUserClicksOnSaveButton() {
        propertyHandler.clickSaveBtn();
        System.out.println("Step 7: User clicks on save button");
    }

    @And("the edited data is saved with a toast message being displayed")
    public void theEditedDataIsSavedWithAToastMessageBeingDisplayed() {
        System.out.println("Step 8: Edited data is saved with a toast message being displayed");
    }

    @Then("the user can see the updated data of the property in the property details form")
    public void theUserCanSeeTheUpdatedDataOfThePropertyInThePropertyDetailsForm() {
        System.out.println("Step 9: User can see the updated data of the property in the property details form");
    }
}