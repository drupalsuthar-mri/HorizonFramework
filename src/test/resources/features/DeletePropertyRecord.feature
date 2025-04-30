Feature: Delete an existing property record
  Background:
    Given the user is signed into the application for delete feature
    And the user is on the home page for delete feature
    And the user navigates to the property list page for delete feature

  Scenario Outline: Successfully delete an existing property record
    Given the user is on the property list page for delete feature
    And the user filters the required property "<propertyName>" using Property search filter for delete feature
    And the user selects the required property "<propertyName>"
    And the user clicks on delete button
    And the user clicks on save button for delete feature
    Then a success message is displayed confirming the property has been deleted
    And the property "<propertyName>" is no longer visible in the property list

    Examples:
      | propertyName |
      | PROPREF5     |