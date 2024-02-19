@UI @Login
Feature: Login with valid credentials

  Scenario Outline: Login with valid credentials
    Given home page is opened
    When valid login credentials were entered
      | <email> | <password> |
    Then user was successfully logged in the application
    Examples:
      | email             | password         |
      | testuser@mail.com | TestUserTestUser |
