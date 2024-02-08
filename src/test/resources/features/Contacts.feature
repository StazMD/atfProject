@UI
Feature: Contacts Feature

  Background: Opening main page
    Given main page is opened
    And valid credentials were entered
      | email             | password         |
      | testuser@mail.com | TestUserTestUser |

  Scenario: Add new contact
    When contact was created
    Then contact displaying in contact list

  Scenario: Delete contact
    Given contact was created
    And contact displaying in contact list
    When contact deleted
    Then contact is no longer in the list of contacts


