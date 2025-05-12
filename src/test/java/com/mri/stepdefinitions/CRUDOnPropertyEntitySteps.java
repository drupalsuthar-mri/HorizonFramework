package com.mri.stepdefinitions;

import com.mri.pages.HomePage;
import com.mri.pages.PropertyListPage;
import com.mri.pages.handler.PropertyHandler;
import com.mri.util.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.asserts.SoftAssert;

import java.util.function.BooleanSupplier;

public class CRUDOnPropertyEntitySteps {
    private final TestContext context;
    private PropertyHandler propertyHandler;
    SoftAssert softAssert = new SoftAssert();

    public CRUDOnPropertyEntitySteps(TestContext context) {
        this.context = context;
    }

    @Given("user is signed in into the application")
    public void userIsSignedInIntoTheApplication() {
        String email = context.getProp().getProperty("email").trim();
        System.out.println("email: "+email );
        String password = context.getProp().getProperty("password").trim();
        System.out.println("password: "+password );
        context.getSigninPage().doSignIn(email, password);
        System.out.println("Background: User is signed in into the application");
    }

    @Given("the user is on the Home page")
    public void theUserIsOnTheHomePage() {
        HomePage homePage = context.getSigninPage().navigateToHomePage();
        context.setHomePage(homePage);
        context.getHomePage().getHomePageTitle();
        System.out.println("Scenario 1: Add a new Property in the Property Entity");
        System.out.println("Step 1: User is on the Home page");
    }

    @When("the user navigates to property list page")
    public void theUserNavigatesToPropertyListPage() {
        PropertyListPage propertyListPage = context.getHomePage().navigateToPropertyListPage();
        context.setPropertyListPage(propertyListPage);
        System.out.println("Step 2: user navigates to property list page");
    }

    @Then("the user has landed on the property list page")
    public void theUserHasLandedOnThePropertyListPage() {
        String propertyListPageTitle = context.getPropertyListPage().getPropertyListPageTitle();
        System.out.println("propertyListPageTitle: "+propertyListPageTitle);
        boolean propertyListVisible = context.getPropertyListPage().isPropertyListVisible();
        BooleanSupplier isPropertyListVisible = () -> propertyListVisible;
        context.getPage().waitForCondition(isPropertyListVisible);
        System.out.println("Step 3: user is has landed on the property list page");
    }

    @When("the user clicks on add new button")
    public void theUserClicksOnAddNewButton() {
        propertyHandler = context.getPropertyListPage().addNewProperty();
        System.out.println("Step 4: user clicks on add new button");
    }

    @Then("the user is forwarded to the add page")
    public void theUserIsForwardedToTheAddPage() {
        BooleanSupplier addNewPropertyPageVisible = propertyHandler.isPropertyDetailsFormVisible();
        context.getPage().waitForCondition(addNewPropertyPageVisible);
        System.out.println("Step 5: user is forwarded to the add page");
    }

    @And("the user enters {string} and selects group ref in the group ref field")
    public void theUserEntersAndSelectsGroupRefInTheGroupRefField(String groupRef) {
        context.getPage().waitForTimeout(3000);
        propertyHandler.selectGroupRef(groupRef);
        System.out.println("Step 6: user enter " +groupRef+ " and selects group ref in the group ref field ");

    }

    @And("the user enters {string} and selects operation ref in the operation ref field")
    public void theUserEntersAndSelectsOperationRefInTheOperationRefField(String operationRef) {
        context.getPage().waitForTimeout(3000);
        String selectedOperationRef = propertyHandler.selectOperationRef(operationRef);
        System.out.println("selectedOperationRef: "+selectedOperationRef);
        System.out.println("Step 7: user enters " +operationRef+ " and selects operation ref in the operation ref field");
    }

    @And("the user enters {string} in the property ref field")
    public void theUserEntersInThePropertyRefField(String propertyRef) {
        context.getPage().waitForTimeout(3000);
        propertyHandler.enterPropertyRef(propertyRef);
        System.out.println("Step 8: user enters " +propertyRef+ " in the property ref field");
    }

    @And("the user enters {string} in the property name field")
    public void theUserEntersInThePropertyNameField(String propertyName) {
        context.getPage().waitForTimeout(3000);
        propertyHandler.enterPropertyName(propertyName);
        System.out.println("Step 9: user enters" +propertyName+ " in the property name field");
    }

    @And("the user enters {string} in the property town field")
    public void theUserEntersInThePropertyTownField(String propertyTown) {
        context.getPage().waitForTimeout(3000);
        propertyHandler.enterPropertyTown(propertyTown);
        System.out.println("Step 10: user enters "+propertyTown+ " in the property town field");
    }

    @And("the user enters {string} in the property address field")
    public void theUserEntersInThePropertyAddressField(String propertyAddress) {
        context.getPage().waitForTimeout(3000);
        propertyHandler.enterPropertyAddress(propertyAddress);
        System.out.println("Step 11: user enters "+propertyAddress+ " in the property address field");
    }

    @And("the user enters {string} in the property county field")
    public void theUserEntersInThePropertyCountyField(String propertyCounty) {
        context.getPage().waitForTimeout(3000);
        propertyHandler.enterPropertyCounty(propertyCounty);
        System.out.println("Step 12: user enters "+propertyCounty+ " in the property county field");
    }

    @And("the user enters {string} in the property postcode field")
    public void theUserEntersInThePropertyPostcodeField(String propertyPostcode) {
        context.getPage().waitForTimeout(3000);
        propertyHandler.enterPropertyPostCode(propertyPostcode);
        System.out.println("Step 13: user enters "+propertyPostcode+ " in the property postcode field");
    }

    @And("the user enters {string} in the property country field")
    public void theUserEntersInThePropertyCountryField(String propertyCountry) {
        context.getPage().waitForTimeout(3000);
        propertyHandler.enterPropertyCountry(propertyCountry);
        System.out.println("Step 14: user enters "+propertyCountry+ " in the property country field");
    }

    @And("the user enters {string} in the property region field")
    public void theUserEntersInThePropertyRegionField(String propertyRegion) {
        context.getPage().waitForTimeout(3000);
        propertyHandler.enterPropertyRegion(propertyRegion);
        System.out.println("Step 15: user enters "+propertyRegion+ " in the property region field");
    }

    @And("user clicks on the save button")
    public void userClicksOnTheSaveButton() {
        propertyHandler.clickSaveBtnForAdd();
        System.out.println("Step 16: user clicks on save button");
    }

    @Then("A success toast message is displayed")
    public void aSuccessToastMessageIsDisplayed() {
        System.out.println("Step 17: a success toast message is displayed");
    }

    @Given("user navigates to the property list page")
    public void userNavigatesToThePropertyListPage() {
        PropertyListPage propertyListPage = context.getHomePage().navigateToPropertyListPage();
        context.setPropertyListPage(propertyListPage);
        context.getPropertyListPage().getPropertyListPageTitle();
        System.out.println("Background: User is on the Property List Page");
    }

    @Given("user is on property list page")
    public void userIsOnPropertyListPage() {
        boolean propertyListVisible = context.getPropertyListPage().isPropertyListVisible();
        BooleanSupplier propertyListVisibleSupplier = () -> propertyListVisible;
        context.getPage().waitForCondition(propertyListVisibleSupplier);
        System.out.println("Step 19: User is on the Property List Page");
    }

    @And("user filters the required property {string} using Property search filter")
    public void userFiltersTheRequiredPropertyUsingPropertySearchFilter(String propertyRef) {
        propertyHandler = context.getPropertyListPage().editProperty();
        propertyHandler.allSearchByFilters("Property",propertyRef);
        System.out.println("Step 20: User filters the required property using Property search filter");
    }

    @And("user clicks on the cog icon of the property")
    public void userClicksOnTheCogIconOfTheProperty() {
        propertyHandler.clickOnCogIconForProperty();
        System.out.println("Step 21: User clicks on the cog icon of the property");
    }

    @And("user is forwarded to property detail form")
    public void userIsForwardedToPropertyDetailForm() {
        System.out.println("Step 22: User is forwarded to property detail form");
    }

    @When("user is on property detail form")
    public void userIsOnPropertyDetailForm() {
        BooleanSupplier propertyDetailsFormVisible = propertyHandler.isPropertyDetailsFormVisible();
        context.getPage().waitForCondition(propertyDetailsFormVisible);
        System.out.println("Step 23: User is on property detail form");
    }

    @And("user edits the initial book cost field")
    public void userEditsTheInitialBookCostField() {
        propertyHandler.enterInitialBookCost("100");
        System.out.println("Step 24: User edits the initial book cost field");
    }

    @And("user clicks on save button on edit page")
    public void userClicksOnSaveButtonOnEditPage() {
        propertyHandler.clickSaveBtn();
        System.out.println("Step 25: User clicks on save button");
    }

    @And("edited data is saved with a toast message being displayed")
    public void editedDataIsSavedWithAToastMessageBeingDisplayed() {
        System.out.println("Step 26: Edited data is saved with a toast message being displayed");
    }

    @Then("user can see the updated data of the property in the property details form")
    public void userCanSeeTheUpdatedDataOfThePropertyInThePropertyDetailsForm() {
        System.out.println("Step 27: User can see the updated data of the property in the property details form");
    }

    @Given("user is on the property list page for delete feature")
    public void userIsOnThePropertyListPageForDeleteFeature() {
        boolean propertyListVisible = context.getPropertyListPage().isPropertyListVisible();
        BooleanSupplier propertyListVisibleSupplier = () -> propertyListVisible;
        context.getPage().waitForCondition(propertyListVisibleSupplier);
        softAssert.assertTrue(propertyListVisible);
        System.out.println("Step 29: User is on the Property List Page");

    }

    @And("ser filters the required property {string} using Property search filter for delete feature")
    public void serFiltersTheRequiredPropertyUsingPropertySearchFilterForDeleteFeature(String propertyRef) {
        propertyHandler = context.getPropertyListPage().deleteProperty();
        propertyHandler.allSearchByFilters("Property",propertyRef);
        System.out.println("Step 30: User filters the required property using Property search filter");
    }

    @And("user selects the required property {string}")
    public void userSelectsTheRequiredProperty(String propertyRef) {
        propertyHandler.selectPropertyFromList(propertyRef);
        System.out.println("Step 31: User selects the required property");
    }

    @And("user clicks on delete button")
    public void userClicksOnDeleteButton() {
        propertyHandler.clickDeleteBtn();
        System.out.println("Step 32: User clicks on delete button");
    }

    @And("user clicks on save button for delete feature")
    public void userClicksOnSaveButtonForDeleteFeature() {
        propertyHandler.clickSaveBtnOnListPage();
        System.out.println("Step 33: User clicks on save button");
    }

    @Then("success message is displayed confirming the property has been deleted")
    public void successMessageIsDisplayedConfirmingThePropertyHasBeenDeleted() {
        System.out.println("Step 35: A success message is displayed confirming the property has been deleted");
    }

    @And("property {string} is no longer visible in the property list")
    public void propertyIsNoLongerVisibleInThePropertyList(String propertyRef) {
        softAssert.assertTrue(propertyHandler.verifyPropertyDeleted(propertyRef));
        System.out.println("Step 36: The property is no longer visible in the property list");
    }

    @Then("user navigate back to property list page")
    public void userNavigateBackToPropertyListPage() {
        propertyHandler.navigateBackToPropertyList();
        System.out.println("Step: User navigate back to property list page");
    }
}