package com.mri.stepdefinitions;

import com.mri.pages.All_menus;
import com.mri.pages.Process_Menu;
import com.mri.util.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProcessSteps {
    private final TestContext context;
    public ProcessSteps(TestContext context) {
        this.context = context;
        this.context.setAllMenu(new All_menus(context.getPage()));
    }
    @Given("User clicks Menu Icon")
    public void userClickOnMenuIcon() {
    System.out.println("User Click on Menu Icon");
    }
    @When("User Click on the SubMenu")
    public void userClickOnProcessTab() {
        context.getAllMenu().ClickSubMenu();
    }
    @Then("User should see the Menu")
    public void processTabIsOpen() {
        System.out.println("Process Tab is opened");
    }
}
