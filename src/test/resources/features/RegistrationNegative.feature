@UI
Feature: Negative Registration Feature

  Background: Opening main page
    Given main page is opened

  Scenario Outline: Registering user with invalid data
    Given adding user page opening
    When "<fieldName>" field submitted with invalid data
    Then error is displaying
    And new user is not created
    Examples:
      | fieldName |
      | firstname |
      | lastname  |
      | email     |
      | password  |
