@API
Feature: Users from API endpoints

  Background: Creating user
    Given new user is created

#  Scenario: Add user from API
#    When new user is able to login
#    Then new user profile could be retrieved

  Scenario: Update and delete user
    When new user profile could be retrieved
    And user is updated
#    And user is deleted
#    Then user not existed


#  Scenario Outline: Get users
#    When get user profile with "<firstname>", "<lastname>", "<email>" and "<password>"
#    Examples:
#      | firstname | lastname | email             | password         |
#      | TestUser  | TestUser | testuser@mail.com | TestUserTestUser |

