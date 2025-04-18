  Feature: Login Feature
    Background:
        Given User open the application
        When User click on the MRI SaaS Okta preview button
        Then User should be redirected to the login page
        And  User enters email in email field
        And User enters password in password field
        Then  User click on the Login Button

    Scenario: Print Menu Name
          Given User should be redirected to the Home Page
          When  User click on the menu button
          Then  User should see the menu options
