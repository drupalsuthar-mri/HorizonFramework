Feature: Login Feature
  Scenario: Login to the application
    Given User open the application
    When User Click on the MRI SaaS Okta preview Button
    Then User should be redirected to the login page
    And  User enters email in email field
    And User enters password in password field
    Then  User click on the Login Button

