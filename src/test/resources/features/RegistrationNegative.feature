Feature: Negative Registration Feature

  Background: Opening main page
    Given main page is opened

  Scenario Outline: User unsuccessfully registered in system
    Given add user button is clicked
    And adding user page opening
    When "<fieldName>" field populated with invalid data
    And [Submit] button is clicked
    Then error is displaying
    And new user is not created
    Examples:
      | fieldName |
      | firstname |
      | lastname  |
      | email     |
      | password  |
