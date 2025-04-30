package com.mri.stepdefinitions;

import com.mri.pages.Accounting;
import com.mri.pages.AddUnit;
import com.mri.util.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.annotations.Test;

public class AccountingSteps {
    private final TestContext context;
    public AccountingSteps(TestContext context) {
        this.context = context;
        this.context.setAccounting(new Accounting(context.getPage()));
    }
    @Given("HomePage is Opened")
    public void homePageIsOpenned() {
        System.out.println("HomePage is Opened");
    }
    @And("User clicks on Menu Icons")
    public void userClickOnMenuIcons(){
        context.getAccounting().clickMenuIcon();
    }
    @And("User clicks on Accounting Tabs")
    public void userClickOnAccountingTab() {
        context.getAccounting().clickAccounting();
    }
    @And("User clicks on the Account Receivable Tab")
    public void userClickOnAccountReceivable() {
        context.getAccounting().clickAccountReceivable();
    }
    @And("User clicks on the Immediate Demand Creation")
    public void userClickOnImmediateDemandsCreation() {
        context.getAccounting().clickImmediateDemandsCreation();
    }
    @Then("Immediate Demand Creation is opened")
    public void immediateDemandCreationIsOpened() {
        System.out.println("Immediate Demand Creation is opened");
    }

    @And("User clicks on the Next Button")
    public void userClickOnNextButton() {
        context.getAccounting().NextBtn();
    }
    @And("User Enters the Tenant Ref into the Tenant Ref field")
    public void userEntersTheTenantRefIntoTheTenantRefField() {
        context.getAccounting().fillTenantRef();
    }
    @And("User Enters the Tax Point Date into the Tax Point Date field")
    public void userEntersTheTaxPointDateIntoTheTaxPointDateField() {
        context.getAccounting().fillTaxPointDate();
    }
    @And("User Enters Message into the Message field")
    public void userEntersMessageIntoTheMessageField() {
        context.getAccounting().Message();
    }
    @And("User Enters the Lease Ref into the Lease Ref field")
    public void userEntersTheLeaseRefIntoTheLeaseRefField() {
        context.getAccounting().fillLeaseRef();
    }
    @And("User Enters the GLAccount into the GLAccount field")
    public void userEntersTheGLAccountIntoTheGLAccountField() {
        context.getAccounting().fillGLAccount();
    }
    @And("User Enters the Major Analysis Code into the Major Analysis Code field")
    public void userEntersTheMajorAnalysisCodeIntoTheMajorAnalysisCodeField() {
        context.getAccounting().fillMajor();
    }
    /*@And("User Enters the Pro-forma Ref into the Pro-forma Ref field")
    public void userEntersTheProFormaRefIntoTheProFormaRefField() {
        context.getAccounting().fillProForma();
    }*/
    @And("User Enters the Payment Plan into the Payment Plan field")
    public void userEntersThePaymentPlanIntoThePaymentPlanField() {
        context.getAccounting().fillPaymentPlan();
    }

    @And("User Enters the From date into the From date field")
    public void userEntersTheFromDateIntoTheFromDateField() {
        context.getAccounting().fillFromDate();
    }
    @And("User Enters the To date into the To date field")
    public void userEntersTheToDateIntoTheToDateField() {
        context.getAccounting().fillToDate();
    }
    @And("User Enter the Area into the Area field")
    public void userEntersTheAreaIntoTheAreaField() {
        context.getAccounting().fillArea();
    }/*
   @And("User enters Descriptions into the Descriptions field")
    public void userEntersDescriptionsIntoTheDescriptionsField() {
        context.getAccounting().fillDescription();
    }
    @And("User Enters the Due On date into the Due On date field")
    public void UserEntersTheDueOnDateIntoTheDueOnDateField() {
        context.getAccounting().FillDueOn();
    }*/
    @And("User Enters Measure into the Measure field")
    public void userEntersMeasureIntoTheMeasureField() {
        context.getAccounting().fillMeasure();
    }
    @And("User Enters the RentalRate into the RentalRate field")
    public void userEntersTheRentalRateIntoTheRentalRateField() {
        context.getAccounting().RentalRate();
    }
   /* @And("User Enters the Net Amount into the Net Amount field")
    public void userEntersTheNetAmountIntoTheNetAmountField() {
        context.getAccounting().Net();
    }
    @And("User Enters Billing Code into the Billing Code field")
    public void userEntersBillingCodeIntoTheBillingCodeField() {
        context.getAccounting().BillingAddr();
    }*/
    /*@And("User Enters the Tax into the Tax field")
    public void userEntersTheTaxIntoTheTaxField() {
        context.getAccounting().fillTaxReq();
    }*/
    @And("User Enters the Comment into the Comment field")
    public void userEntersTheCommentIntoTheCommentField() {
        context.getAccounting().fillComment();
    }
    @And("User click on the Save buttons")
    public void userClickOnTheSaveButtons() {
        context.getAccounting().ClickSaveBtn();
    }
    @Then("Pop up is opened")
    public void popUpIsOpened() {
        System.out.println("Pop up is opened");

    }
    @And("User click on the Yes button")
    public void userClickOnTheYesButton() {
        context.getAccounting().ClickYes();
    }
    @Then("Pop up is closed")
    public void popUpIsClosed() {
        System.out.println("Pop up is closed");
    }
    @Then("The Immediate Demand Creation is saved successfully")
    public void userShouldSeeTheImmediateDemandCreationIsSavedSuccessfully() {
        System.out.println("User should see the Immediate Demand Creation is saved successfully");
    }

}
