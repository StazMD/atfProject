@API
Feature: Delete User Via API

  Scenario Outline: Successfully deleting an existing user
    Given valid user data
      | firstName   | lastName   | email   | password   |
      | <firstName> | <lastName> | <email> | <password> |
    And a request to login with user's details was sent
    When a request to delete the user was sent
    Then the user was successfully deleted

    Examples:
      | firstName         | lastName         | email         | password         |
      | [randomFirstName] | [randomLastName] | [randomEmail] | [randomPassword] |
#      | Ion               | IAonascu         | Ion.IAonascu@email.com | ion.gutuion.gutu