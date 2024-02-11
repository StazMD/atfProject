package stepDefinition.UI;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.ContactDetailsPage;
import pages.ContactListPage;
import utils.TestDataGeneratorUtils;

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
        contactListPage.assertHeader("Add Contact");
        new TestDataGeneratorUtils().generateContactCredentials();
        contactListPage.fillingContactFields();
    }

    @Then("contact displaying in contact list")
    public void contactDisplayingInContactList() throws InterruptedException {
        contactListPage.verifyEntryIsPresent();
    }

    @When("contact deleted")
    public void contactDeleted() throws InterruptedException {
        contactListPage.getContactDetails();
        contactDetailsPage.deleteContactButton();
    }

    @Then("contact is no longer in the list of contacts")
    public void contactIsNoLongerInTheListOfContacts() {
        contactListPage.verifyEntryIsAbsent();
    }
}
