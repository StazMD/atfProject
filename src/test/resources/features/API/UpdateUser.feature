@API
Feature: Update User Via API

  Scenario Outline: Successfully update values of an existing user
    Given valid user data
      | firstName | lastName | email                 | password     |
      | Ion       | [random] | Ion.Usturoi@email.com | Va.RoiVa.Roi |
    When a request to update the user's details with next values was sent
      | firstName   | lastName   | email   | password   |
      | <firstName> | <lastName> | <email> | <password> |
    Then the user's details was successfully updated

    Examples:
      | firstName | lastName | email                 | password     |
      | Andrei    | Usturoi  | Ion.Usturoi@email.com | Va.RoiVa.Roi |
