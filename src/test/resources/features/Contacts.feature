@UI @Contact
Feature: Contacts Feature

  Background: Opening main page
    Given home page is opened
    And valid credentials were entered
      | email             | password         |
      | testuser@mail.com | TestUserTestUser |

  Scenario: Creating contact
    When contact was created
    Then contact displaying in contact list

  Scenario: Deleting contact
    Given contact was created
    And contact displaying in contact list
    When contact was deleted
    Then contact is no longer in the list of contacts


