@UI
Feature: Positive Registration Feature

  Background: Opening main page
    Given main page is opened

  Scenario: Register user using valid data
    When adding user page opening
    And all fields are submitted with valid data
    Then new user was created
