package stepDefinition.UI;

import context.ScenarioContext;
import entity.Contact;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import pages.ContactDetailsPage;
import pages.ContactListPage;

import java.util.List;
import java.util.stream.Collectors;

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

    public Contact extractContactData() {
        try {
            return (Contact) scenarioContext.getContext("contact");
        } catch (RuntimeException ex) {
            throw new RuntimeException("message", ex);
        }
    }

    @And("contact was created")
    public void contactWasCreated() {
        contactListPage.openAddContactPage();
        Contact contact = extractContactData();
        contactListPage.contactFields(contact);
    }

    @Then("contact displaying in contact list")
    public void contactDisplayingInContactList() {
        Contact contact = extractContactData();
        List<WebElement> contactsTableRows = contactListPage.getRowsFromTable();

        String searchText = contact.getFirstNameLastName();
        List<WebElement> filteredRows = contactsTableRows.stream().filter(row -> row.getText().contains(searchText)).collect(Collectors.toList());

        assertThat(filteredRows).isNotEmpty();
    }

    @When("contact was deleted")
    public void contactDeleted() {
        Contact contact = extractContactData();
        contactListPage.getContactFromTable(contact.getFirstNameLastName());
        contactDetailsPage.deleteContactAction();
    }

    @Then("contact is no longer in the list of contacts")
    public void contactIsNoLongerInTheListOfContacts() {
        Contact contact = extractContactData();
        List<WebElement> contactsTableRows = contactListPage.getRowsFromTable();

        String searchText = contact.getFirstNameLastName();
        List<WebElement> filteredRows = contactsTableRows.stream().filter(row -> row.getText().contains(searchText)).collect(Collectors.toList());

        assertThat(filteredRows).isEmpty();
    }
}
