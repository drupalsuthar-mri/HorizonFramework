package com.mri.stepdefinitions;

import com.mri.pages.HomePage;
import com.mri.pages.PropertyListPage;
import com.mri.pages.handler.PropertyHandler;
import com.mri.util.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.util.function.BooleanSupplier;

public class DeletePropertyRecordSteps {
    private final TestContext context;
    private PropertyHandler propertyHandler;

    public DeletePropertyRecordSteps(TestContext context) {
        this.context = context;
    }

    @Given("the user is signed into the application for delete feature")
    public void theUserIsSignedIntoTheApplicationForDeleteFeature() {
        String email = context.getProp().getProperty("email").trim();
        String password = context.getProp().getProperty("password").trim();
        context.getSigninPage().doSignIn(email, password);
        System.out.println("Background: User is already Signed in");
    }

    @And("the user is on the home page for delete feature")
    public void theUserIsOnTheHomePageForDeleteFeature() {
        HomePage homePage = context.getSigninPage().navigateToHomePage();
        context.setHomePage(homePage);
        context.getHomePage().getHomePageTitle();
        System.out.println("Background: User is on the Home Page");
    }

    @And("the user navigates to the property list page for delete feature")
    public void theUserNavigatesToThePropertyListPageForDeleteFeature() {
        PropertyListPage propertyListPage = context.getHomePage().navigateToPropertyListPage();
        context.setPropertyListPage(propertyListPage);
        context.getPropertyListPage().getPropertyListPageTitle();
        System.out.println("Background: User is on the Property List Page");
    }

    @Given("the user is on the property list page for delete feature")
    public void theUserIsOnThePropertyListPageForDeleteFeature() {
        boolean propertyListVisible = context.getPropertyListPage().isPropertyListVisible();
        BooleanSupplier propertyListVisibleSupplier = () -> propertyListVisible;
        context.getPage().waitForCondition(propertyListVisibleSupplier);
        System.out.println("Step 1: User is on the Property List Page");
    }

    @And("the user filters the required property {string} using Property search filter for delete feature")
    public void theUserFiltersTheRequiredPropertyUsingPropertySearchFilterForDeleteFeature(String propertyRef) {
        propertyHandler = context.getPropertyListPage().editProperty();
        propertyHandler.allSearchByFilters("Property",propertyRef);
        System.out.println("Step 2: User filters the required property using Property search filter");
    }

    @And("the user selects the required property {string}")
    public void theUserSelectsTheRequiredProperty(String propertyRef) {
        propertyHandler.selectPropertyFromList(propertyRef);
        System.out.println("Step 3: User selects the required property");
    }

    @And("the user clicks on delete button")
    public void theUserClicksOnDeleteButton() {
        propertyHandler.clickDeleteBtn();
        System.out.println("Step 4: User clicks on delete button");
    }

    @And("the user clicks on save button for delete feature")
    public void theUserClicksOnSaveButtonForDeleteFeature() {
        propertyHandler.clickSaveBtnOnListPage();
        System.out.println("Step 5: User clicks on save button");
    }

    @Then("a success message is displayed confirming the property has been deleted")
    public void aSuccessMessageIsDisplayedConfirmingThePropertyHasBeenDeleted() {
        System.out.println("Step 6: A success message is displayed confirming the property has been deleted");
    }

    @And("the property {string} is no longer visible in the property list")
    public void thePropertyIsNoLongerVisibleInThePropertyList(String propertyRef) {
        propertyHandler.verifyPropertyDeleted(propertyRef);
        System.out.println("Step 7: The property is no longer visible in the property list");
    }
}