package stepDefinition.UI;

import context.ScenarioContext;
import entity.Contact;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.ContactDetailsPage;
import pages.ContactListPage;

public class ContactsTest {

    private final ContactListPage contactListPage;
    private final ContactDetailsPage contactDetailsPage;
    private final ScenarioContext scenarioContext = ScenarioContext.INSTANCE;

    public Contact extractContactData() {
        try {
            return (Contact) scenarioContext.getContext("contact");
        } catch (RuntimeException ex) {
            throw new RuntimeException("message", ex);
        }
    }

    public ContactsTest(ContactListPage contactListPage, ContactDetailsPage contactDetailsPage) {
        this.contactListPage = contactListPage;
        this.contactDetailsPage = contactDetailsPage;
    }

    @And("contact was created")
    public void contactWasCreated() {
        contactListPage.addNewContactButton();
        contactListPage.assertHeader("Add Contact");
        Contact contact = extractContactData();
        String firstNameLastName = contact.getFirstName() + " " + contact.getLastName();
        contactListPage.contactFields(contact);
//        contactListPage.contactFields(contact.getFirstName(), contact.getLastName(), contact.getBirthdate(), contact.getEmail(), contact.getPhone(), contact.getStreet1(), contact.getStreet2(), contact.getCity(), contact.getStateProvince(), contact.getPostalCode(), contact.getCountry());
//        contactListPage.contactFields(scenarioContext.getContext("contact"));
    }

    @Then("contact displaying in contact list")
    public void contactDisplayingInContactList() throws InterruptedException {
        contactListPage.verifyEntryIsPresent();
    }

    @When("contact was deleted")
    public void contactDeleted() throws InterruptedException {
        contactListPage.getContactDetails();
        contactDetailsPage.deleteContactButton();
    }

    @Then("contact is no longer in the list of contacts")
    public void contactIsNoLongerInTheListOfContacts() {
        contactListPage.verifyEntryIsAbsent();
    }
}
