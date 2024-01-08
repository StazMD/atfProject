package steps.contacts;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.ContactListPage;
import pages.MainPage;

public class ContactsTest {

    private final MainPage mainPage;
    private final ContactListPage contactListPage;

    public ContactsTest(MainPage mainPage, ContactListPage contactListPage) {
        this.mainPage = mainPage;
        this.contactListPage = contactListPage;
    }

    @When("adding contact page opened")
    public void addingContactPageOpened() {
        contactListPage.AddNewContactButton();
        contactListPage.assertHeader("Add Contact", true);
    }

    @And("all fields populated with valid data")
    public void allFieldsPopulatedWithValidData() {
        contactListPage.populatingContactFields();
    }

    @Then("contact is successfully created")
    public void contactIsSuccessfullyCreated() {

    }
}
