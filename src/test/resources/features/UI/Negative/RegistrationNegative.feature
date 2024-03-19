@UI @Negative
Feature: Registration With Invalid Credentials

  Scenario Outline: Register User Using Invalid Data
    Given home page is opened
    And adding user page opening
    When '<fieldName>' submitted with invalid data
    Then new user with '<fieldName>' is not created
    And error for '<fieldName>' is displaying
    Examples:
      | fieldName |
      | firstName |
      | lastName  |
      | email     |
      | password  |
