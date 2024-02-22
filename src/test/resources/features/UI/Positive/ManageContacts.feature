@UI @Contact
Feature: Manage Users Contacts

  Background: Opening Main Page
    Given home page is opened
    And valid login credentials were entered
      | email             | password         |
      | testuser@mail.com | TestUserTestUser |

  Scenario: Creating Contact
    When contact was created
    Then contact displaying in contact list

  Scenario: Deleting Contact
    Given contact was created
    And contact displaying in contact list
    When contact was deleted
    Then contact is no longer in the list of contacts
