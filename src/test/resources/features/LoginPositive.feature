@UI
Feature: Positive Login Feature

  Background: Opening main page
    Given main page is opened

  Scenario Outline: Log In user
    When valid "<userEmail>" and "<userPassword>" were entered
    And [Submit] button is clicked
    Then user was successfully logged in
    Examples:
      | userEmail         | userPassword     |
      | TestUser@mail.com | TestUserTestUser |