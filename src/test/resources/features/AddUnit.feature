
Feature: Add Unit
    Background:
      Given User is Login to the application

  @ExcelData
  Scenario: Add New Unit
    Given User adds all units from Excel
    Given HomePage is Open
    And User clicks on Menu Icon
    And User clicks on DataBase Tab
    And User clicks on the Unit Tab
    Then Unit List is opened

    When User clicks on  New button
    And User enters the Property into the Property Ref field
    And User enters the <Floor> Ref into the Floor Ref field
    And User enters the <Unit> Ref into the Unit Ref field
    And User enters Description into the Description field
    And User enters the <StartDate> into the Start Date field
    And User enters the <EndDate> into the End Date field
    And User click on the Save button
    Then Unit is added successfully

