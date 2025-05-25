package com.mri.stepdefinitions;

import com.mri.pages.Update;
import com.mri.util.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UpdateStep {
    private final TestContext context;


    public UpdateStep(TestContext context) {
        this.context = context;
        this.context.setUpdate(new Update(context.getPage()));
    }



    @Given("User clicks on the Menu Icon")
    public void userClickOnTheMenuIcon() {
        context.getUpdate().ClickMenuIcon();
    }

    @And("User clicks on the DataBase Tab")
    public void userClickOnDataBaseTab() {
        context.getUpdate().ClickDatabase();
    }

    @And("User clicks on the Property Tab")
    public void userClickOnPropertyTab() {
        context.getUpdate().GetProperty();
    }

    @Then("Property List Page is opened")
    public void propertyListIsOpen() {
        System.out.println("Property List is visible");
    }

    @When("User enters the Property Reference into the Property Reference field")
    public void TheUserEntersThePropertyReferenceIntoThePropertyReferenceField() {
        context.getUpdate().SearchProperty();

    }

    @And("User presses Enter")
    public void userPressEnter() {
        context.getPage().keyboard().press("Enter");


    }

    @Then("Property detail page is opened")
    public void propertyDetailPageIsOpen() {
        context.getUpdate().ClickDetailBtn();
        System.out.println("Property Detail is visible");
    }

    @When("User enters the city into the City field")
    public void UserEntersTheCityIntoTheCityField() {
        context.getUpdate().UpdateProperty();
    }

    @And("User clicks on the Save button")
    public void userClickOnSaveButton() {
        context.getUpdate().ClickSaveBtn();
    }

    @Then("Property details are updated successfully")
    public void propertyDetailsAreUpdatedSuccessfully() {
        System.out.println("Property details are updated successfully");
    }
}

