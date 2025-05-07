Feature: Update Unit
Background:
    Given User is Login to the application

Scenario: Update Unit
    Given User clicks on  Menu Icon
    And User clicks on  DataBase Tab
    And User clicks on Unit Tabs
    When User enters the Unite Reference in the Unit Reference Search field and clicks on the cog icon
    Then The Unit Detail page should be opened
    And User Enter the Zone Ref of Unit
    And User Enters the type of Unit
    And User Enter the Asset Type of Unit
    And User clicks on  Save button
    Then The Unit should be updated successfully















