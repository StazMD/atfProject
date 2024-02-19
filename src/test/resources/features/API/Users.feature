@API @User
Feature: Manage user via API endpoints

  Scenario: Creating, updating and deleting user
    Given new user is created
    And new user profile could be retrieved
    And new user is able to login
    When user is updated
    And user is deleted
    Then user not existed
