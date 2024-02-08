@UI
Feature: Negative Registration Feature

  Scenario Outline: Register user using invalid data
    Given main page is opened
    When adding user page opening
    And '<fieldName>' field submitted with invalid data
    Then error is displaying
    And new user is not created
    Examples:
      | fieldName |
      | firstName |
      | lastName  |
      | email     |
      | password  |
