@UI @DB
Feature: Registration With Valid Credentials

  Scenario: Register User Using Valid Data
    Given home page is opened
    When adding user page opening
    And all fields are submitted with valid data
    Then new user was created
