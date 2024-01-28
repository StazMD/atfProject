Feature: Get users

  Scenario Outline: Get users
    When get user profile with "<firstname>", "<lastname>", "<email>" and "<password>"
    Examples:
      | firstname | lastname | email             | password         |
      | TestUser  | TestUser | testuser@mail.com | TestUserTestUser |

