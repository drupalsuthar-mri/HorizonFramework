Feature: Delete Unit Record
  Background:
    Given User is Login to the application

  Scenario: Delete Unit
    Given User clicks on  Menu Icon
    And User clicks on  DataBase Tab
    And User clicks on Unit Tabs
    When User enters the Unite Reference in the Unit Reference Search field
    Then User clicks on delete Button
    And User clicks on The Save button
    Then The Unit should be deleted successfully