Feature: Edit an existing record

  Background:
    Given the user is signed into the application
    And the user is on the home page
    And the user navigates to the property list page

  Scenario Outline: Successfully edit an existing record
    Given the user is on the property list page
    And the user filters the required property "<propertyName>" using Property search filter
    And the user clicks on the cog icon of the property
    And the user is forwarded to property detail form
    When the user is on property detail form
    And the user edits the initial book cost field
    And the user clicks on save button
    And the edited data is saved with a toast message being displayed
    Then the user can see the updated data of the property in the property details form
    Examples:
      | propertyName |
      | DASPROP1     |