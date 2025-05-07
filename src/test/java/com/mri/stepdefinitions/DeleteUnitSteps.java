package com.mri.stepdefinitions;

import com.mri.pages.AddUnit;
import com.mri.util.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DeleteUnitSteps {
    private final TestContext context;
    public  DeleteUnitSteps(TestContext context) {
        this.context = context;
        this.context.setAddUnit(new AddUnit(context.getPage()));
    }

    @When("User enters the Unite Reference in the Unit Reference Search field")
    public void userEntersTheUnitReferenceInTheUnitReferenceSearchField() {
        context.getAddUnit().SearchUnit();
    }

    @Then("User clicks on delete Button")
    public void UserClickOnDeleteIcon() {
        context.getAddUnit().DeleteUnit();
    }
    @And("User clicks on The Save button")
    public void userClickOnTheSaveButton() {
        context.getAddUnit().SaveBtn();
    }
    @Then("The Unit should be deleted successfully")
    public void theUnitShouldBeDeletedSuccessfully() {
        System.out.println("The Unit should be deleted successfully");
    }
}
