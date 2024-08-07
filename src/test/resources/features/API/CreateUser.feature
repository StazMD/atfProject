@API
Feature: Create User Via API

  Scenario Outline: Successfully creating a new user
    Given valid user data
      | firstName   | lastName   | email   | password   |
      | <firstName> | <lastName> | <email> | <password> |
    When a request to create new user was sent
    Then user was successfully created

    Examples:
      | firstName         | lastName         | email         | password         |
      | [randomFirstName] | [randomLastName] | [randomEmail] | [randomPassword] |
#      | Ion               | Gutul            | [randomEmail]          | ion.ion.ion       |
#      | Anton             | Moraru           | Anton.Moraru@email.com | anton.anton.anton |
