package com.mri.stepdefinitions;


import com.mri.util.TestContext;
import com.mri.pages.AddUnit;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import com.mri.util.ExcelData;
import lombok.SneakyThrows;

import javax.xml.stream.events.StartDocument;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.mri.util.TestNGExtentReporter.test;

public class AddUnitSteps {
    private final TestContext context;
    public AddUnitSteps(TestContext context) {
        this.context = context;
        this.context.setAddUnit(new AddUnit(context.getPage()));
    }
    List<Map<String, String>> data;
    @Before
    public void loadData() throws IOException {
        data = ExcelData.readExcelData("src/test/resources/testdata/SourceData1.xlsx", "Sheet1");
    }
    @Given("User adds all units from Excel")
    public void add_units_from_excel() {
        for (Map<String, String> row : data) {
            // Simulate the actions using each row
            System.out.println("Adding Unit: " + row.get("Unit"));

            // Example:
            // driver.findElement(By.id("floorRef")).sendKeys(row.get("Floor"));
            // ...
        }
    }
    @Given("HomePage is Open")
    public void homePageIsOpen() {
        System.out.println("HomePage is Open");
    }
    @SneakyThrows
    @And("User clicks on Menu Icon")
    public void userClickOnMenuIcon() {
        Thread.sleep(2000);
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
    @And("User enters the Property into the Property Ref field")
    public void userEntersThePropertyRefIntoThePropertyRefField() {
        context.getAddUnit().PropertyRef();
    }
    @And("User enters the (.*) into the Floor Ref field")
    public void userEntersTheFloorIntoTheFloorField(String Floor) {
        context.getAddUnit().Floor(Floor);
    }
    @And("User enters the (.*) into the Unit Ref field")
    public void userEntersTheUnitRefIntoTheUnitRefField(String UnitRef) {
        context.getAddUnit().UnitRef(UnitRef);
    }
    @And("User enters Description into the Description field")
    public void userClickOnAddUnitButton() {
        context.getAddUnit().Description();
    }

    @And("User enters the (.*) into the Start Date field")
    public void UserEntersTheStartDateIntoTheStartDateField(String StartDate) {
        context.getAddUnit().StartDate(StartDate);
    }
    @And("User enters the (.*) into the End Date field")
    public void userEntersTheEndDateIntoTheEndDateField(String EndDate) {
        context.getAddUnit().EndDate(EndDate);
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
