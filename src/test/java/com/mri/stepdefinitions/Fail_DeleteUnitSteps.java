package com.mri.stepdefinitions;

import com.mri.pages.AddUnit;
import com.mri.util.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Fail_DeleteUnitSteps {
    private final TestContext context;
    public  Fail_DeleteUnitSteps(TestContext context) {
        this.context = context;
        this.context.setAddUnit(new AddUnit(context.getPage()));
    }
    @When("User enters Invalid Unite Reference in the Unit Reference Search field")
    public void userEntersTheUnitReferenceInTheUnitReferenceSearchField() {
        context.getPage().waitForTimeout(3000);
        context.getAddUnit().SearchInvalidUnit();
    }
    @Then("User clicks on the delete Button")
    public void UserClickOnDeleteIcon() {
        context.getPage().waitForTimeout(3000);
        context.getAddUnit().Delete();
    }
    @Then("User is unable to delete Unit Unable to delete the unit")
    public void userClickOnTheSaveButton() {
       System.out.println("User is unable to delete Unit Unable to delete the unit");
    }

    @Then("The Unit should be deleted successfully from unit list")
    public void theUnitShouldBeDeletedSuccessfully() {
        System.out.println("The Unit should not be deleted successfully");
    }

}
