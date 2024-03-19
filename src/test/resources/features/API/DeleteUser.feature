@API
Feature: Delete User Via API

  Scenario Outline: Successfully deleting user
    Given valid user data
      | firstName   | lastName   | email   | password   |
      | <firstName> | <lastName> | <email> | <password> |
    And a request to login with user's details was sent
    When a request to delete user was sent
    Then the user was successfully deleted

    Examples:
      | firstName | lastName | email                  | password          |
      | Anton     | Moraru   | Anton.Moraru@email.com | anton.anton.anton |
