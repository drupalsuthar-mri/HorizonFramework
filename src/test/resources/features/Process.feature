Feature: Process Menu
  Background:
    Given User is Login to the application

  Scenario: Click Process Menu
    Given User clicks Menu Icon
    When User Click on the SubMenu
    Then User should see the Menu
