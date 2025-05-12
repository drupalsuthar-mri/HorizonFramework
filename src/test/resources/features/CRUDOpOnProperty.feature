Feature: CRUD operations on Property Entity

  Background:
    Given user is signed in into the application


  Scenario Outline: Add a new Property in the Property Entity
    Given the user is on the Home page
    When the user navigates to property list page
    Then the user has landed on the property list page
    When the user clicks on add new button
    Then the user is forwarded to the add page
    And the user enters "<group ref>" and selects group ref in the group ref field
    And the user enters "<operation ref>" and selects operation ref in the operation ref field
    And the user enters "<property ref>" in the property ref field
    And the user enters "<property name>" in the property name field
    And the user enters "<property town>" in the property town field
    And the user enters "<property address>" in the property address field
    And the user enters "<property county>" in the property county field
    And the user enters "<property postcode>" in the property postcode field
    And the user enters "<property country>" in the property country field
    And the user enters "<property region>" in the property region field
    And user clicks on the save button
    Then A success toast message is displayed

#  Edit the created property record
    Then user navigate back to property list page
    Given user is on property list page
    And user filters the required property "<propertyName>" using Property search filter
    And user clicks on the cog icon of the property
    And user is forwarded to property detail form
    When user is on property detail form
    And user edits the initial book cost field
    And user clicks on save button on edit page
    And edited data is saved with a toast message being displayed
    Then user can see the updated data of the property in the property details form
    Then user navigate back to property list page
#    Delete the created property
    Given user is on the property list page for delete feature
    And ser filters the required property "<propertyName>" using Property search filter for delete feature
    And user selects the required property "<propertyName>"
    And user clicks on delete button
    And user clicks on save button for delete feature
    Then success message is displayed confirming the property has been deleted
    And property "<propertyName>" is no longer visible in the property list

#    For Failure
    And ser filters the required property "<propertyName>" using Property search filter for delete feature
    And user selects the required property "<propertyName>"
    And user clicks on delete button
    And user clicks on save button for delete feature
    Then success message is displayed confirming the property has been deleted
    And property "<propertyName>" is no longer visible in the property list



    Examples:
      | group ref | operation ref | property ref | property name                    | property town | property address          | property county | property postcode | property country | property region | propertyName | propertyName |
      | DASGROP1  | DASCOMP1      | PROPREF5     | DAS Automation Framework testing | Bristol       | 123 Automation Avenue DAS | Avon            | BS5 9HT           | United Kingdom   | South East      | PROPREF5     | PROPREF5     |