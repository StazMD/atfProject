package steps.tests;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.BasePage;
import pages.ContactDetailsPage;
import pages.ContactListPage;

public class ContactsTest {

    private final ContactListPage contactListPage;
    private final ContactDetailsPage contactDetailsPage;
    private final BasePage basePage;

    public ContactsTest(ContactListPage contactListPage, ContactDetailsPage contactDetailsPage, BasePage basePage) {
        this.contactListPage = contactListPage;
        this.contactDetailsPage = contactDetailsPage;
        this.basePage = basePage;
    }

    @And("contact was created")
    public void contactWasCreated() {
        contactListPage.addNewContactPageWithButton();
        basePage.assertHeader("Add Contact", true);
        contactListPage.fillingContactFields();
    }

    @Then("contact displaying in contact list")
    public void contactDisplayingInContactList() {
        contactListPage.findEntry();
    }

    @And("list of contacts is opened")
    public void listOfContactsIsOpened() {
        basePage.assertHeader("Contact List", true);
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
