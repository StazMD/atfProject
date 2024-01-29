@UI
Feature: Positive Login Feature

  Scenario Outline: Log In user
    When main page is opened
    And valid "<userEmail>" and "<userPassword>" were entered
    Then user was successfully logged in the application
    Examples:
      | userEmail         | userPassword     |
      | testuser@mail.com | TestUserTestUser |