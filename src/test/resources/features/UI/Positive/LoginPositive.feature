@UI @LoginTest
Feature: Login With Valid Credentials

  Scenario Outline: Login With Valid Credentials
    Given home page is opened
    When valid login credentials were entered
      | email   | password   |
      | <email> | <password> |
    Then user was successfully logged in the application
    Examples:
      | email             | password         |
      | testuser@mail.com | TestUserTestUser |
