@login
Feature: Adding New Contact Feature

  Background: Opening main page
    Given main page is opened
    And user was successfully logged in

  Scenario: Add new contact
    When adding contact page opened
    And all fields populated with valid data
    And [Submit] button is clicked
    Then contact is successfully created
    And new contact is displaying in contact list with fields