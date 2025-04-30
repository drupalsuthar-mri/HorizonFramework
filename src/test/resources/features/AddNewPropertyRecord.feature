Feature: Add New Record in Property

  Background:
    Given User is already Signed in

  Scenario Outline: User clicks on the add new button for new property generation
    Given User is on the Home page
    When user navigates to property list page
    Then user is on the property list page
    When user clicks on add new button
    Then user is forwarded to the add page
    And user enters "<group ref>" and selects group ref in the group ref field
    And user enters "<operation ref>" and selects operation ref in the operation ref field
    And user enters "<property ref>" in the property ref field
    And user enters "<property name>" in the property name field
    And user enters "<property town>" in the property town field
    And user enters "<property address>" in the property address field
    And user enters "<property county>" in the property county field
    And user enters "<property postcode>" in the property postcode field
    And user enters "<property country>" in the property country field
    And user enters "<property region>" in the property region field
    And user clicks on save button
    Then a success toast message is displayed
    Examples:
      | group ref | operation ref | property ref | property name                    | property town | property address          | property county | property postcode | property country | property region |
      | DASGROP1  | DASCOMP1      | propref5     | DAS Automation Framework testing | Bristol       | 123 Automation Avenue DAS | Avon            | BS5 9HT           | United Kingdom   | South East      |