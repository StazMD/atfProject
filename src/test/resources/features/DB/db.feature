@DB
Feature: Manage Values

  Scenario Outline: Adding Values
    When values '<firstName>', '<lastName>', '<email>', '<password>' added to the database
    Then values '<firstName>', '<lastName>', '<email>', '<password>' is in the database
    Examples:
      | firstName | lastName | email           | password     |
      | John      | Smith    | jsmith@mail.com | JohnPassword |

