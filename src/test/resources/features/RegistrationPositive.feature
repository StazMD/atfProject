@UI
Feature: Positive Registration Feature

  Scenario: Register user using valid data
    Given home page is opened
    When adding user page opening
    And all fields are submitted with valid data
    Then new user was created
