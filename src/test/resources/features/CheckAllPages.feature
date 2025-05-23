Feature: Check all the pages are working or not
  Background:
    Given the user has signed into the application

    Scenario: check all the pages are working or not by clicking on them
      Given the user has navigated to the home page
      When the user navigates to each side menu
      Then the user verifies the pages are working or not