@API @User
Feature: Manage User Via API Endpoints

  Scenario: Creating, Updating And Deleting user
    Given new user is created
    And new user profile could be retrieved
    And new user is able to login
    When user is updated
    And user is deleted
    Then user not existed

  Scenario: Successfully creating a new user
    Given I send a request to create a new user
    Then the user should be successfully created
    And I should receive a confirmation response with the user's details

