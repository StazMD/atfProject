@API
Feature: Delete User Via API

  Scenario Outline: Successfully deleting user
     
    And a request to login with user's details was sent
    When a request to delete user was sent
    Then the user was successfully deleted

    Examples:
      | firstName | lastName | email                  | password          |
      | Anton     | Moraru   | Anton.Moraru@email.com | anton.anton.anton |
