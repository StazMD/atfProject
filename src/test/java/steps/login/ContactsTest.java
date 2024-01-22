package steps.login;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.ContactListPage;

public class ContactsTest {

    private final ContactListPage contactListPage;

    public ContactsTest(ContactListPage contactListPage) {
        this.contactListPage = contactListPage;
    }

    @When("adding contact page opened")
    public void addingContactPageOpened() {
        contactListPage.AddNewContactButton();
        contactListPage.assertHeader("Add Contact", true);
    }

    @And("all fields submitted with valid data")
    public void allFieldsPopulatedWithValidData() {
        contactListPage.fillingContactFields();
    }

    @Then("contact is successfully created and displaying in contact list")
    public void contactIsSuccessfullyCreated() {
        contactListPage.assertEntry();

    }
}
