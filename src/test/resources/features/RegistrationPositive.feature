Feature: Positive Registration Feature

  Background: Opening main page
    Given main page is opened

  Scenario: Register user
    When add user button is clicked
    And adding user page opening
    And all fields are populated with correct data
    And [Submit] button is clicked
    Then new user is created
