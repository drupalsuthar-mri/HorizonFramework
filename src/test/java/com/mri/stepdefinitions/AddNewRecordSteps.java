package com.mri.stepdefinitions;

import com.mri.pages.HomePage;
import com.mri.pages.PropertyListPage;
import com.mri.pages.handler.AddNewHandler;
import com.mri.util.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.function.BooleanSupplier;

public class AddNewRecordSteps {
    private final TestContext context;
    private AddNewHandler addNewHandler;

    public AddNewRecordSteps(TestContext context) {
        this.context = context;
    }

    @Given("User is already Signed in")
    public void userIsAlreadySignedIn() {
        String email = context.getProp().getProperty("email").trim();
        System.out.println("email: "+email );
        String password = context.getProp().getProperty("password").trim();
        System.out.println("password: "+password );
        context.getSigninPage().doSignIn(email, password);
        System.out.println("Background: User is already Signed in");
    }

    @Given("User is on the Home page")
    public void userIsOnTheHomePage() {
        HomePage homePage = context.getSigninPage().navigateToHomePage();
        context.setHomePage(homePage);
        context.getHomePage().getHomePageTitle();
        System.out.println("Step 1: User is on the Home page");
    }

    @When("user navigates to property list page")
    public void userNavigatesToPropertyListPage() throws InterruptedException {
        PropertyListPage propertyListPage = context.getHomePage().navigateToPropertyListPage();
        context.setPropertyListPage(propertyListPage);
        System.out.println("Step 2: user navigates to property list page");
    }

    @Then("user is on the property list page")
    public void userIsOnThePropertyListPage() {
        String propertyListPageTitle = context.getPropertyListPage().getPropertyListPageTitle();
        System.out.println("propertyListPageTitle: "+propertyListPageTitle);
        Boolean propertyListVisible = context.getPropertyListPage().isPropertyListVisible();
        BooleanSupplier isPropertyListVisible = () -> propertyListVisible;
        context.getPage().waitForCondition(isPropertyListVisible);
        System.out.println("Step 3: user is on the property list page");
    }

    @When("user clicks on add new button")
    public void userClicksOnAddNewButton() {
        addNewHandler = context.getPropertyListPage().clickAddNewPropertyBtn();
        System.out.println("Step 4: user clicks on add new button");
    }

    @Then("user is forwarded to the add page")
    public void userIsForwardedToTheAddPage() {
        BooleanSupplier addNewPropertyPageVisible = addNewHandler.isAddNewPropertyPageVisible();
        context.getPage().waitForCondition(addNewPropertyPageVisible);
        System.out.println("Step 5: user is forwarded to the add page");
    }

    @And("user enters and selects group ref in the group ref field")
    public void userEntersAndSelectsGroupRefInTheGroupRefField() {
        context.getPage().waitForTimeout(3000);
        addNewHandler.selectGroupRef();
        System.out.println("Step 6: user enters and selects group ref in the group ref field");
    }

    @And("user enters and selects operation ref in the operation ref field")
    public void userEntersAndSelectsOperationRefInTheOperationRefField() {
        String selectedOperationRef = addNewHandler.selectOperationRef();
        System.out.println("selectedOperationRef: "+selectedOperationRef);
        System.out.println("Step 7: user enters and selects operation ref in the operation ref field");
    }

    @And("user enters property name in the property name field")
    public void userEntersPropertyNameInThePropertyNameField() {
        addNewHandler.enterPropertyName();
        System.out.println("Step 8: user enters property name in the property name field");
    }

    @And("user enters property town in the property town field")
    public void userEntersPropertyTownInThePropertyTownField() {
        addNewHandler.enterPropertyTown();
        System.out.println("Step 9: user enters property town in the property town field");
    }

    @And("user enters property address in the property address field")
    public void userEntersPropertyAddressInThePropertyAddressField() {
        addNewHandler.enterPropertyAddress();
        System.out.println("Step 10: user enters property address in the property address field");
    }

    @And("user enters property county in the property county field")
    public void userEntersPropertyCountyInThePropertyCountyField() {
        addNewHandler.enterPropertyCounty();
        System.out.println("Step 11: user enters property county in the property county field");
    }

    @And("user enters property postcode in the property postcode field")
    public void userEntersPropertyPostcodeInThePropertyPostcodeField() {
        addNewHandler.enterPropertyPostCode();
        System.out.println("Step 12: user enters property postcode in the property postcode field");
    }

    @And("user enters property country in the property country field")
    public void userEntersPropertyCountryInThePropertyCountryField() {
        addNewHandler.enterPropertyCountry();
        System.out.println("Step 13: user enters property country in the property country field");
    }

    @And("user enters property region in the property region field")
    public void userEntersPropertyRegionInThePropertyRegionField() {
        addNewHandler.enterPropertyRegion();
        System.out.println("Step 14: user enters property region in the property region field");
    }

    @And("user clicks on save button")
    public void userClicksOnSaveButton() {
        addNewHandler.clickSaveBtn();
        System.out.println("Step 15: user clicks on save button");
    }

    @Then("a success toast message is displayed")
    public void aSuccessToastMessageIsDisplayed() {
        System.out.println("Step 16: a success toast message is displayed");
    }

}