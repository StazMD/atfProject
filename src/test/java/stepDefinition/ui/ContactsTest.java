package stepDefinition.ui;

import context.ScenarioContext;
import entity.ContactEntity;
import enums.Entity;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import pages.ContactDetailsPage;
import pages.ContactListPage;
import utils.CustomException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ContactsTest {

    private final ContactListPage contactListPage;
    private final ContactDetailsPage contactDetailsPage;
    private final ScenarioContext scenarioContext = ScenarioContext.INSTANCE;
    private static final Logger log = LogManager.getLogger(ContactsTest.class);

    public ContactsTest(ContactListPage contactListPage, ContactDetailsPage contactDetailsPage) {
        this.contactListPage = contactListPage;
        this.contactDetailsPage = contactDetailsPage;
    }

    public ContactEntity extractContactData() {
        try {
            log.debug("Attempting to extract contact data from scenario context");
            return (ContactEntity) scenarioContext.getContext(Entity.CONTACT);
        } catch (RuntimeException ex) {
            log.error("Failed to extract contact data from scenario context", ex);
            throw new CustomException("Contact Data could not be extracted", true);
        }
    }

    @And("contact was created")
    public void contactWasCreated() {
        try {
            log.info("Opening Add Contact Page");
            contactListPage.openAddContactPage();
            ContactEntity contactEntity = extractContactData();
            log.info("Filling contact fields for '{}'", contactEntity.getFirstNameLastName());
            contactListPage.fillContactFields(contactEntity);
        } catch (TimeoutException ex) {
            throw new CustomException(ex.getMessage());
        }
    }

    @Then("contact displaying in contact list")
    public void contactDisplayingInContactList() {
        ContactEntity contactEntity = extractContactData();
        log.info("Checking if contact '{}' is displaying in contact list", contactEntity.getFirstNameLastName());
        List<WebElement> contactsTableRows = contactListPage.getRowsFromTable();

        String searchText = contactEntity.getFirstNameLastName();
        List<WebElement> filteredRows = contactsTableRows.stream()
                .filter(row -> row.getText().contains(searchText))
                .toList();

        assertThat(filteredRows).isNotEmpty();
        log.info("Contact '{}' successfully found in contact list", searchText);
    }

    @When("contact was deleted")
    public void contactDeleted() {
        ContactEntity contactEntity = extractContactData();
        log.info("Deleting contact '{}'", contactEntity.getFirstNameLastName());
        contactListPage.getContactFromTable(contactEntity.getFirstNameLastName());
        contactDetailsPage.deleteContact();
    }

    @Then("contact is no longer in the list of contacts")
    public void contactIsNoLongerInTheListOfContacts() {
        ContactEntity contactEntity = extractContactData();
        log.info("Verifying that contact '{}' is no longer in the list of contacts", contactEntity.getFirstNameLastName());
        List<WebElement> contactsTableRows = contactListPage.getRowsFromTable();

        String searchText = contactEntity.getFirstNameLastName();
        List<WebElement> filteredRows = contactsTableRows.stream()
                .filter(row -> row.getText().contains(searchText))
                .toList();

        assertThat(filteredRows).isEmpty();
        log.info("Contact '{}' is successfully removed from contact list", searchText);
    }
}
