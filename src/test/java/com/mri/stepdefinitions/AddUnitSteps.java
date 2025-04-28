package com.mri.stepdefinitions;

import com.mri.util.TestContext;
import com.mri.pages.AddUnit;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddUnitSteps {
    private final TestContext context;
    public AddUnitSteps(TestContext context) {
        this.context = context;
        this.context.setAddUnit(new AddUnit(context.getPage()));
    }

    @Given("HomePage is Open")
    public void homePageIsOpen() {
        System.out.println("HomePage is Open");
    }
    @And("User clicks on Menu Icon")
    public void userClickOnMenuIcon() {
        context.getAddUnit().ClickMenuIcon();
    }
    @And("User clicks on DataBase Tab")
    public void userClickOnTheDatabaseIcon() {
        context.getAddUnit().ClickDatabase();
    }
    @And("User clicks on the Unit Tab")
    public void userClickOnTheUnitTab() {
        context.getAddUnit().ClickUnit();
    }
    @Then("Unit List is opened")
    public void unitListIsOpened() {
        System.out.println("Unit List is opened");
    }
   @When("User clicks on  New button")
    public void userClickOnNewButton() {
        context.getAddUnit().ClickNewBtn();
    }

    @And("User enters the Property Ref into the Property Ref field")
    public void userEntersThePropertyRefIntoThePropertyRefField() {
        context.getAddUnit().PropertyRef();
    }
    @And("User enters the Floor Ref into the Floor Ref field")
    public void userEntersTheFloorIntoTheFloorField() {
        context.getAddUnit().Floor();
    }
    @And("User enters Description into the Description field")
    public void userClickOnAddUnitButton() {
        context.getAddUnit().Description();
    }

    @And("User enters the Start Date into the Start Date field")
    public void UserEntersTheStartDateIntoTheStartDateField() {
        context.getAddUnit().StartDate();
    }
    @And("User enters the End Date into the End Date field")
    public void userEntersTheEndDateIntoTheEndDateField() {
        context.getAddUnit().EndDate();
    }

    @And("User click on the Save button")
    public void userClickOnSaveButton() {
        context.getAddUnit().ClickSaveBtn();
    }
    @Then("Unit is added successfully")
    public void UnitIsAddedSuccessfully() {
        System.out.println("Unit is added successfully");
    }


}
