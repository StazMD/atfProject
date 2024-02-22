@API
Feature: Create User Via API

#  Scenario: Creating, Updating And Deleting user
#    Given new user is created
#    And new user profile could be retrieved
#    And new user is able to login
#    When user is updated
#    And user is deleted
#    Then user not existed

  Scenario Outline: Successfully creating a new user
    Given valid user data
      | firstName   | lastName   | email   | password   |
      | <firstName> | <lastName> | <email> | <password> |
    When a request to create new user was sent
    Then user was successfully created
    Examples:
      | firstName         | lastName         | email                  | password         |
      | [randomFirstName] | [randomLastName] | [randomEmail]          | [randomPassword] |
      | Ion               | IAonascu         | Ion.IAonascu@email.com | ion.gutuion.gutu |

Feature: Update User Via API

  Scenario: Successfully updating an existing user
    Given an existing user with known user ID
    When I send a request to update the user's details
    Then the user's details should be successfully updated
    And I should receive a confirmation response with the updated details

Feature: Delete User Via API

  Scenario: Successfully deleting an existing user
    Given an existing user with known user ID
    When I send a request to delete the user
    Then the user should be successfully deleted
    And a subsequent request for the user's details should confirm the user does not exist
