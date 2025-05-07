package com.mri.stepdefinitions;

import com.mri.pages.AddUnit;
import com.mri.util.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UpdateUnitSteps {
    private final TestContext context;
    public UpdateUnitSteps(TestContext context) {
        this.context = context;
        this.context.setAddUnit(new AddUnit(context.getPage()));
    }

    @Given("User clicks on  Menu Icon")
    public void userClickOnMenuIcon() {
        context.getAddUnit().ClickMenuIcon();
    }

    @And("User clicks on  DataBase Tab")
    public void userClickOnTheDatabaseIcon() {
        context.getAddUnit().ClickDatabase();
    }
    @And("User clicks on Unit Tabs")
    public void userClickOnUnitTabs() {
        context.getAddUnit().ClickUnit();
    }
    @When("User enters the Unite Reference in the Unit Reference Search field and clicks on the cog icon")
    public void userEntersTheUnitReferenceInTheUnitReferenceSearchFieldAndClickonTheCogIcon() {
        context.getAddUnit().UpdateUnit();
    }
    @Then("The Unit Detail page should be opened")
    public void theUnitDetailPageShouldBeOpened() {
        System.out.println("The Unit Detail page should be opened");
    }
    @And("User Enter the Zone Ref of Unit")
    public void userEntersTheZoneRef() {
        context.getAddUnit().ZoneRef();
    }

    @And("User Enters the type of Unit")
    public void userEntersTheTypeOfUnit() {
        context.getAddUnit().UnitType();
    }

    @And("User Enter the Asset Type of Unit")
    public void userEntersTheAssetType() {
        context.getAddUnit().AssetType();
    }

    @And("User clicks on  Save button")
    public void userClickOnSaveButton() {
        context.getAddUnit().ClickSaveBtn();
    }

    @Then("The Unit should be updated successfully")
    public void theUnitShouldBeUpdatedSuccessfully() {
        System.out.println("The Unit should be updated successfully");
    }


}
