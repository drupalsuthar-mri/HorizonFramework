Feature: Immediate Demand Creation
  Background:
   Given User is Login to the application

  Scenario: Create an immediate demand
    Given HomePage is Opened
    And User clicks on Menu Icons
    And User clicks on Accounting Tabs
    And User clicks on the Account Receivable Tab
    And User clicks on the Immediate Demand Creation
    Then Immediate Demand Creation is opened
    And User clicks on the Next Button
    And User Enters the Tenant Ref into the Tenant Ref field
    And User Enters the Tax Point Date into the Tax Point Date field
    And User Enters Message into the Message field
    And User Enters the Lease Ref into the Lease Ref field
    And User Enters the GLAccount into the GLAccount field
    And User Enters the Major Analysis Code into the Major Analysis Code field
#    And User Enters the Pro-forma Ref into the Pro-forma Ref field
    And User Enters the Payment Plan into the Payment Plan field
    And User Enters the From date into the From date field
    And User Enters the To date into the To date field
    And User Enter the Area into the Area field
#    And User enters Descriptions into the Descriptions field
#    And User Enters the Due On date into the Due On date field
    And User Enters Measure into the Measure field
    And User Enters the RentalRate into the RentalRate field
#    And User Enters the Net Amount into the Net Amount field
#    And User Enters Billing Code into the Billing Code field
#    And User Enters the Tax into the Tax field
    And User Enters the Comment into the Comment field
    And User click on the Save buttons
    Then Pop up is opened
    And User click on the Yes button
    Then Pop up is closed
    Then The Immediate Demand Creation is saved successfully


