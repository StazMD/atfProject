package steps.login;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.ContactDetailsPage;
import pages.ContactListPage;

public class ContactsTest {

    private final ContactListPage contactListPage;
    private final ContactDetailsPage contactDetailsPage;

    public ContactsTest(ContactListPage contactListPage, ContactDetailsPage contactDetailsPage) {
        this.contactListPage = contactListPage;
        this.contactDetailsPage = contactDetailsPage;
    }

    @And("contact was created")
    public void contactWasCreated() {
        contactListPage.addNewContactPageWithButton();
        contactListPage.assertHeader("Add Contact", true);
        contactListPage.fillingContactFields();
    }

    @Then("contact displaying in contact list")
    public void contactDisplayingInContactList() {
        contactListPage.findEntry();
    }

    @And("list of contacts is opened")
    public void listOfContactsIsOpened() {
        contactListPage.assertHeader("Contact List", true);
    }

    @When("contact deleted")
    public void contactDeleted() {
        contactListPage.getContactDetails();
        contactDetailsPage.deleteAction();
    }

    @Then("contact is no longer in the list of contacts")
    public void contactIsNoLongerInTheListOfContacts() {
        contactListPage.findEntry();
    }
}
