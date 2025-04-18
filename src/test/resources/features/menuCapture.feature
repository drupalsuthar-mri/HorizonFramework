Feature: End to End test of the app
  Background:
    Given user is on Welcome page
    When user clicks on MRI Okta button
    And user is forwarded to Okta login page
    And user enters email in the email address field
    And user enters password in the password field
    And user clicks on remember me checkbox
    And user clicks on sign in button
    Then user is redirected to the MRI home page

  Scenario: Capture screenshots for each side menu item
    When the user opens the side menu
    Then the user navigates and captures screenshot of the side menu