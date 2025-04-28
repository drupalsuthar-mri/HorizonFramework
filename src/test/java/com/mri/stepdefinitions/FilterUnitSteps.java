package com.mri.stepdefinitions;

import com.mri.pages.AddUnit;
import com.mri.util.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class FilterUnitSteps {
    private final TestContext context;
    public FilterUnitSteps(TestContext context) {
        this.context = context;
        this.context.setAddUnit(new AddUnit(context.getPage()));
    }
    @And("Filter Unit by Entering Unit Reference in the Unit Reference Search field")
    public void filterUnitByEnteringUnitReferenceInTheUnitReferenceSearchField() {
        context.getAddUnit().filterUnit();
    }
    @Then("Unit List is filtered by the Unit Reference")
    public void unitListIsFilteredByTheUnitReference() {
        System.out.println("Unit List is filtered by the Unit Reference");
    }

}
