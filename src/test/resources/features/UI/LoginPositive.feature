@UI
Feature: Positive Login Feature

  Scenario Outline: Log In user
    Given home page is opened
    When valid credentials were entered
      | <email> | <password> |
    Then user was successfully logged in the application
    Examples:
      | email             | password         |
      | testuser@mail.com | TestUserTestUser |
