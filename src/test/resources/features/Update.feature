Feature: Update Property Details
  Background:
    Given User is Logged in to the application
  Scenario:
    Given User clicks on the Menu Icon
    And User clicks on the DataBase Tab
    And User clicks on the Property Tab
    Then Property List is opened

    When User enters the Property Reference into the Property Reference field
    And User presses Enter
    Then Property detail page is opened

    When User enters the city into the City field
    And User clicks on the Save button
    Then Property details are updated successfully
