@UI @DB @Negative
Feature: Registration With Invalid Credentials

  Scenario Outline: Register User Using Invalid Data
    Given home page is opened
    When adding user page opening
    And '<fieldName>' submitted with invalid data
    Then error is displaying
    And new user with '<fieldName>' is not created
    Examples:
      | fieldName |
      | firstName |
      | lastName  |
      | email     |
      | password  |
