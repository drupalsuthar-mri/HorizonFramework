Feature: Fail Delete Unit
  Background:
    Given User is Login to the application

  Scenario: Fail Delete Unit
  Given User clicks on  Menu Icon
  And User clicks on  DataBase Tab
  And User clicks on Unit Tabs
  When User enters Invalid Unite Reference in the Unit Reference Search field
  Then User clicks on the delete Button
  Then User is unable to delete Unit Unable to delete the unit
  Then The Unit should be deleted successfully from unit list
