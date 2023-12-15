Feature: Login feature

  Scenario Outline: User successfully log in
    When user "<user>" logged in
#    Then user successfully logged in
    Examples:
      | user  |
      | admin |
