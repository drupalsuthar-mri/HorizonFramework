package com.mri.stepdefinitions;

import com.mri.util.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddPropertySteps {

    private  final TestContext context;

    public AddPropertySteps(TestContext context) {
        this.context = context;
        this.context.setAddProperty(new com.mri.pages.AddProperty(context.getPage()));
    }

    @Given("User is Login to the application")
    public void userIsLoggedInToTheApplication() {
        String url= context.getProp().getProperty("url").trim();
        context.getPage().navigate(url);
        context.getLoginPage().DoLogin(context.getProp().getProperty("email"),
                context.getProp().getProperty("password"));
    }

    @Given("HomePage is Opened")
    public void HomepageIsOpened() {
        System.out.println("HomePage is Opened");

    }

    @And ("User clicks on the Menu Icon")
    public void userClickOnTheMenuIcon() {
        context.getAddProperty().ClickMenuIcon();
    }

    @And("User clicks on the DataBase Tab")
    public void userClickOnDataBaseTab() {
        context.getAddProperty().ClickDatabase();
    }

    @And("User clicks on the Property Tab")
    public void userClickOnPropertyTab() {
        context.getAddProperty().ClickProperty();
    }

    @Then("Property List is opened")
    public void propertyListIsOpen() {
        System.out.println("Property List is visible");
    }

    @When("User clicks on the New button")
    public void userClickOnNewButton() {
        context.getAddProperty().ClickNewBtn();
    }


    @And("User enters the Group Ref into the Group Ref field")
    public void userEntersTheGroupReferenceIntoTheGroupReferenceField() {
        context.getAddProperty().FillGroupRef();
    }

    @And("User enters the Operation Ref into the Operation Ref field")
    public void userEntersTheOperationalReferenceIntoTheOperationalReferenceField() {
        context.getAddProperty().FillOperationalRef();
    }



    @And("User click on the Save button")
//*

    public void userClicksOnTheSaveButton() {
        context.getAddProperty().ClickSaveBtn();
    }

    @Then("Property is added successfully")
    public void propertyIsAddedSuccessfully() {
        System.out.println("Property is added successfully");
    }


}
