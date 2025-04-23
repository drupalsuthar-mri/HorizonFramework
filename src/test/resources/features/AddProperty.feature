Feature: Add Property
  Background:
    Given User is Login to the application

    Scenario:
      Given HomePage is Opened
      And User clicks on the Menu Icon
      And User clicks on the DataBase Tab
      And User clicks on the Property Tab
      Then Property List is opened

      When User clicks on the New button
      And User enters the Group Ref into the Group Ref field
      And User enters the Operation Ref into the Operation Ref field
      And User click on the Save button
      Then Property is added successfully




