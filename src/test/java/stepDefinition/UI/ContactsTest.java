package stepDefinition.UI;

import context.ScenarioContext;
import entity.ContactEntity;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import pages.ContactDetailsPage;
import pages.ContactListPage;
import utils.ExceptionUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
            return (ContactEntity) scenarioContext.getContext("contact");
        } catch (RuntimeException ex) {
            throw new ExceptionUtils("Contact Data could not be extracted");
        }
    }

    @And("contact was created")
    public void contactWasCreated() {
        contactListPage.openAddContactPage();
        ContactEntity contactEntity = extractContactData();
        contactListPage.fillContactFields(contactEntity);
    }

    @Then("contact displaying in contact list")
    public void contactDisplayingInContactList() {
        ContactEntity contactEntity = extractContactData();
        List<WebElement> contactsTableRows = contactListPage.getRowsFromTable();

        String searchText = contactEntity.getFirstNameLastName();
        List<WebElement> filteredRows = contactsTableRows.stream().filter(row -> row.getText().contains(searchText)).toList();

        assertFalse(filteredRows.isEmpty());
    }

    @When("contact was deleted")
    public void contactDeleted() {
        ContactEntity contactEntity = extractContactData();
        contactListPage.getContactFromTable(contactEntity.getFirstNameLastName());
        contactDetailsPage.deleteContactAction();
    }

    @Then("contact is no longer in the list of contacts")
    public void contactIsNoLongerInTheListOfContacts() {
        ContactEntity contactEntity = extractContactData();
        List<WebElement> contactsTableRows = contactListPage.getRowsFromTable();

        String searchText = contactEntity.getFirstNameLastName();
        List<WebElement> filteredRows = contactsTableRows.stream().filter(row -> row.getText().contains(searchText)).toList();

        assertTrue(filteredRows.isEmpty());
    }
}
