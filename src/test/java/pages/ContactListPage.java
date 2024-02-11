package pages;

import context.ScenarioContext;
import entity.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.WaitUtils;

import java.util.List;

public class ContactListPage extends BasePage {

    private static final ScenarioContext scenarioContext;
    private String firstNameLastName;

    //TODO why?
    static {
        scenarioContext = ScenarioContext.INSTANCE;
    }

    @FindBy(xpath = "//*[@id='add-contact']")
    private WebElement addContactButton;

    @FindBy(xpath = "//*[@id='firstName']")
    private WebElement firstNameElement;

    @FindBy(xpath = "//*[@id='lastName']")
    private WebElement lastNameElement;

    @FindBy(xpath = "//*[@id='birthdate']")
    private WebElement birthdateElement;

    @FindBy(xpath = "//*[@id='email']")
    private WebElement emailElement;

    @FindBy(xpath = "//*[@id='phone']")
    private WebElement phoneElement;

    @FindBy(xpath = "//*[@id='street1']")
    private WebElement street1Element;

    @FindBy(xpath = "//*[@id='street2']")
    private WebElement street2Element;

    @FindBy(xpath = "//*[@id='city']")
    private WebElement cityElement;

    @FindBy(xpath = "//*[@id='stateProvince']")
    private WebElement stateProvinceElement;

    @FindBy(xpath = "//*[@id='postalCode']")
    private WebElement postalCodeElement;

    @FindBy(xpath = "//*[@id='country']")
    private WebElement countryElement;

    @FindBy(xpath = "//*[@id='myTable']")
    private WebElement contactsTable;

    @FindBy(className = "contactTableBodyRow")
    private List<WebElement> contactTableBodyRowElements;

    public Contact extractContactData() {
        try {
            return (Contact) scenarioContext.getContext("contact");
        } catch (RuntimeException ex) {
            throw new RuntimeException("message", ex);
        }
    }

    public void addNewContactPageWithButton() {
        WaitUtils.waitForElement(addContactButton, 10).click();
    }

    public void fillingContactFields() {

        Contact contact = extractContactData();
        firstNameLastName = contact.getFirstName() + " " + contact.getLastName();

        WaitUtils.waitForElement(firstNameElement, 10).sendKeys(contact.getFirstName());
        WaitUtils.waitForElement(lastNameElement, 10).sendKeys(contact.getLastName());
        WaitUtils.waitForElement(birthdateElement, 10).sendKeys(contact.getBirthdate());
        WaitUtils.waitForElement(emailElement, 10).sendKeys(contact.getEmail());
        WaitUtils.waitForElement(phoneElement, 10).sendKeys(contact.getPhone());
        WaitUtils.waitForElement(street1Element, 10).sendKeys(contact.getStreet1());
        WaitUtils.waitForElement(street2Element, 10).sendKeys(contact.getStreet2());
        WaitUtils.waitForElement(cityElement, 10).sendKeys(contact.getCity());
        WaitUtils.waitForElement(stateProvinceElement, 10).sendKeys(contact.getStateProvince());
        WaitUtils.waitForElement(postalCodeElement, 10).sendKeys(contact.getPostalCode());
        WaitUtils.waitForElement(countryElement, 10).sendKeys(contact.getCountry());

        clickSubmitButton();
    }

    public WebElement verifyEntryIsPresent() {
        WaitUtils.waitForElement(contactsTable, 10);
        for (WebElement contactsTable : contactTableBodyRowElements) {
            List<WebElement> cells = contactsTable.findElements(By.tagName("td"));
            for (WebElement cell : cells) {
                if (cell.getText().equals(firstNameLastName)) {
                    log.info("Entry found: " + firstNameLastName);
                    return cell;
                }
            }
        }
        throw new NoSuchElementException("Entry with name " + firstNameLastName + " not found.");
    }

    public void verifyEntryIsAbsent() {
        WaitUtils.waitForElement(contactsTable, 10);
        for (WebElement contactsTable : contactTableBodyRowElements) {
            List<WebElement> cells = contactsTable.findElements(By.tagName("td"));
            for (WebElement cell : cells) {
                if (cell.getText().equals(firstNameLastName)) {
                    log.info("Entry still present: " + firstNameLastName);
                }
            }
        }
        log.info("Entry absent as expected: " + firstNameLastName);
    }

    public void getContactDetails() {
        WebElement contact = verifyEntryIsPresent();
        if (contact != null) {
            contact.click();
        } else {
            log.info("Entry to open was not found.");
        }
    }
}
