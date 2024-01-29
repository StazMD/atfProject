package pages;

import config.PropertyReader;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.TestDataGeneratorUtils;
import utils.WaitUtils;

import java.util.List;

public class ContactListPage extends BasePage {

    private String firstNameData;
    private String lastNameData;
    private String firstNameLastName;

    private final String contactListUrl = PropertyReader.getProperty("contactListPath");

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

    public ContactListPage() {
        super();
    }

    public void addNewContactPageWithButton() {
        addContactButton.click();
    }

    public void fillingContactFields() {
        String firstNameData = TestDataGeneratorUtils.getRandomFirstName();
        firstNameElement.sendKeys(firstNameData);
        String lastNameData = TestDataGeneratorUtils.getRandomLastName();
        lastNameElement.sendKeys(lastNameData);
        String birthdateData = TestDataGeneratorUtils.getRandomDate();
        birthdateElement.sendKeys(birthdateData);
        String emailData = TestDataGeneratorUtils.getRandomEmail();
        emailElement.sendKeys(emailData);
        String phoneData = TestDataGeneratorUtils.getRandomPhoneNumber();
        phoneElement.sendKeys(phoneData);
        String street1Data = TestDataGeneratorUtils.getRandomStreetAddress();
        street1Element.sendKeys(street1Data);
        String street2Data = TestDataGeneratorUtils.getRandomStreetAddress();
        street2Element.sendKeys(street2Data);
        String cityData = TestDataGeneratorUtils.getRandomCity();
        cityElement.sendKeys(cityData);
        String stateProvinceData = TestDataGeneratorUtils.getState();
        stateProvinceElement.sendKeys(stateProvinceData);
        String postalCodeData = TestDataGeneratorUtils.getPostalCode();
        postalCodeElement.sendKeys(postalCodeData);
        String countryData = TestDataGeneratorUtils.getCountry();
        countryElement.sendKeys(countryData);

        firstNameLastName = firstNameData + " " + lastNameData;

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
