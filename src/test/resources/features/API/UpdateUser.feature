@API
Feature: Update User Via API

  Scenario Outline: Successfully update values of an existing user
    Given valid user data
      | firstName | lastName | email                 | password     |
      | Ion       | Usturoi  | Ion.Usturoi@email.com | Va.RoiVa.Roi |
    And a request to login with user's details was sent
    When a request to update the user's details with next values was sent
      | firstName   | lastName   | email   | password   |
      | <firstName> | <lastName> | <email> | <password> |
    Then the user's details was successfully updated

    Examples:
      | firstName | lastName | email                 | password     |
      | Vasile    | Usturoi  | Ion.Usturoi@email.com | Va.RoiVa.Roi |
