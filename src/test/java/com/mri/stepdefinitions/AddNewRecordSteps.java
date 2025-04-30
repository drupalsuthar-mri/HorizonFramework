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

public class AddNewRecordSteps {
    private final TestContext context;
    private PropertyHandler propertyHandler;

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
        boolean propertyListVisible = context.getPropertyListPage().isPropertyListVisible();
        BooleanSupplier isPropertyListVisible = () -> propertyListVisible;
        context.getPage().waitForCondition(isPropertyListVisible);
        System.out.println("Step 3: user is on the property list page");
    }

    @When("user clicks on add new button")
    public void userClicksOnAddNewButton() {
        propertyHandler = context.getPropertyListPage().addNewProperty();
        System.out.println("Step 4: user clicks on add new button");
    }

    @Then("user is forwarded to the add page")
    public void userIsForwardedToTheAddPage() {
        BooleanSupplier addNewPropertyPageVisible = propertyHandler.isPropertyDetailsFormVisible();
        context.getPage().waitForCondition(addNewPropertyPageVisible);
        System.out.println("Step 5: user is forwarded to the add page");
    }

    @And("user enters {string} and selects group ref in the group ref field")
    public void userEntersAndSelectsGroupRefInTheGroupRefField(String groupRef) {
        context.getPage().waitForTimeout(3000);
        propertyHandler.selectGroupRef(groupRef);
        System.out.println("Step 6: user enter " +groupRef+ " and selects group ref in the group ref field ");
    }

    @And("user enters {string} and selects operation ref in the operation ref field")
    public void userEntersAndSelectsOperationRefInTheOperationRefField(String operationRef) {
        context.getPage().waitForTimeout(3000);
        String selectedOperationRef = propertyHandler.selectOperationRef(operationRef);
        System.out.println("selectedOperationRef: "+selectedOperationRef);
        System.out.println("Step 7: user enters " +operationRef+ " and selects operation ref in the operation ref field");
    }

    @And("user enters {string} in the property ref field")
    public void userEntersInThePropertyRefField(String propertyRef) {
        context.getPage().waitForTimeout(3000);
        propertyHandler.enterPropertyRef(propertyRef);
        System.out.println("Step 8: user enters " +propertyRef+ " in the property ref field");
    }

    @And("user enters {string} in the property name field")
    public void userEntersInThePropertyNameField(String propertyName) {
        context.getPage().waitForTimeout(3000);
        propertyHandler.enterPropertyName(propertyName);
        System.out.println("Step 9: user enters" +propertyName+ " in the property name field");
    }

    @And("user enters {string} in the property town field")
    public void userEntersInThePropertyTownField(String propertyTown) {
        context.getPage().waitForTimeout(3000);
        propertyHandler.enterPropertyTown(propertyTown);
        System.out.println("Step 10: user enters "+propertyTown+ " in the property town field");
    }

    @And("user enters {string} in the property address field")
    public void userEntersInThePropertyAddressField(String propertyAddress) {
        context.getPage().waitForTimeout(3000);
        propertyHandler.enterPropertyAddress(propertyAddress);
        System.out.println("Step 11: user enters "+propertyAddress+ " in the property address field");
    }

    @And("user enters {string} in the property county field")
    public void userEntersInThePropertyCountyField(String propertyCounty) {
        context.getPage().waitForTimeout(3000);
        propertyHandler.enterPropertyCounty(propertyCounty);
        System.out.println("Step 12: user enters "+propertyCounty+ " in the property county field");
    }

    @And("user enters {string} in the property postcode field")
    public void userEntersInThePropertyPostcodeField(String propertyPostcode) {
        context.getPage().waitForTimeout(3000);
        propertyHandler.enterPropertyPostCode(propertyPostcode);
        System.out.println("Step 13: user enters "+propertyPostcode+ " in the property postcode field");
    }

    @And("user enters {string} in the property country field")
    public void userEntersInThePropertyCountryField(String propertyCountry) {
        context.getPage().waitForTimeout(3000);
        propertyHandler.enterPropertyCountry(propertyCountry);
        System.out.println("Step 14: user enters "+propertyCountry+ " in the property country field");
    }

    @And("user enters {string} in the property region field")
    public void userEntersInThePropertyRegionField(String propertyRegion) {
        context.getPage().waitForTimeout(3000);
        propertyHandler.enterPropertyRegion(propertyRegion);
        System.out.println("Step 15: user enters "+propertyRegion+ " in the property region field");
    }

    @And("user clicks on save button")
    public void userClicksOnSaveButton() {
        propertyHandler.clickSaveBtn();
        System.out.println("Step 16: user clicks on save button");
    }

    @Then("a success toast message is displayed")
    public void aSuccessToastMessageIsDisplayed() {
        System.out.println("Step 17: a success toast message is displayed");
    }
}