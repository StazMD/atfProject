@UI @API
Feature: Negative Registration Feature

  Background: Opening main page
    Given main page is opened

  Scenario Outline: Register user using invalid data
    When adding user page opening
    And "<fieldName>" field submitted with invalid data
    Then error is displaying
    And new user is not created
    Examples:
      | fieldName |
      | firstname |
      | lastname  |
      | email     |
      | password  |
