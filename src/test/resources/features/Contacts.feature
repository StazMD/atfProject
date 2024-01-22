@login
Feature: Contacts Feature

  Background: Opening main page
    Given main page is opened
    And user was successfully logged in

  Scenario: Add new contact
    When adding contact page opened
    And all fields submitted with valid data
    Then contact is successfully created and displaying in contact list

  Scenario Outline: Delete contact
    Given list of contacts is opened
    And "<contact>" is in the list
    When contact deleted
    Then contact is no longer in the list of contacts
    Examples:
      | contact    |
      | John Smith |
