Feature: Filter Unit
  Background:
    Given User is Login to the application

    Scenario:
      Given User clicks on  Menu Icon
      And User clicks on  DataBase Tab
      And User clicks on Unit Tabs
      And Filter Unit by Entering Unit Reference in the Unit Reference Search field
      Then Unit List is filtered by the Unit Reference