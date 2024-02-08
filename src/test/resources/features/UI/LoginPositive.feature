@UI
Feature: Positive Login Feature

  Scenario: Log In user
    Given main page is opened
    When valid credentials were entered
      | email             | password         |
      | testuser@mail.com | TestUserTestUser |
    Then user was successfully logged in the application
