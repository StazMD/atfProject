@API
Feature: Delete User Via API

  Scenario Outline: Successfully deleting an existing user
    Given valid user data
      | firstName   | lastName   | email   | password   |
      | <firstName> | <lastName> | <email> | <password> |
    And a request to create new user was sent
    When a request to delete user was sent
    Then the user was successfully deleted

    Examples:
      | firstName         | lastName         | email         | password         |
      | [randomFirstName] | [randomLastName] | [randomEmail] | [randomPassword] |
#      | Ion               | IAonascu         | Ion.IAonascu@email.com | ion.gutuion.gutu