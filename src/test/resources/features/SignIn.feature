Feature: Sign in Feature
  Scenario: Sign in with valid credentials
    Given user is on Welcome page
    When user clicks on MRI Okta button
    And user is forwarded to Okta login page
    And user enters email in the email address field
    And user enters password in the password field
    And user clicks on remember me checkbox
    And user clicks on sign in button
    Then user is redirected to the MRI home page