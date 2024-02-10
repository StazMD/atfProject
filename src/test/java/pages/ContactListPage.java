package pages;

import context.ScenarioContext;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.TestDataGeneratorUtils;
import utils.WaitUtils;

import java.util.List;

public class ContactListPage extends BasePage {

    private String firstNameLastName;

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

    public void addNewContactPageWithButton() {
        WaitUtils.waitForElement(addContactButton, 10).click();
    }

    public void fillingContactFields() {
        ScenarioContext scenarioContext = ScenarioContext.INSTANCE;
        //TODO make a method in ScenarioContext to put MAP from here, same setContext method with different attributes(override method)
        String firstNameData = TestDataGeneratorUtils.getRandomFirstName();
        String lastNameData = TestDataGeneratorUtils.getRandomLastName();
        String birthdateDate = TestDataGeneratorUtils.getRandomDate();
        String emailData = TestDataGeneratorUtils.getRandomEmail();
        String phoneData = TestDataGeneratorUtils.getRandomPhoneNumber();
        String street1Data = TestDataGeneratorUtils.getRandomStreetAddress();
        String street2Data = TestDataGeneratorUtils.getRandomStreetAddress();
        String cityData = TestDataGeneratorUtils.getRandomCity();
        String stateProvinceData = TestDataGeneratorUtils.getState();
        String postalCodeData = TestDataGeneratorUtils.getPostalCode();
        String countryData = TestDataGeneratorUtils.getCountry();
        firstNameLastName = firstNameData + " " + lastNameData;

        scenarioContext.setContext("contactFirstName", firstNameData);
        scenarioContext.setContext("contactLastName", lastNameData);
        scenarioContext.setContext("contactBirthday", birthdateDate);
        scenarioContext.setContext("contactEmail", emailData);
        scenarioContext.setContext("contactPhone", phoneData);
        scenarioContext.setContext("contactStreet1", street1Data);
        scenarioContext.setContext("contactStreet2", street2Data);
        scenarioContext.setContext("contactCity", cityData);
        scenarioContext.setContext("contactStateProvince", stateProvinceData);
        scenarioContext.setContext("contactPostalCode", postalCodeData);
        scenarioContext.setContext("contactCountry", countryData);

        WaitUtils.waitForElement(firstNameElement, 10).sendKeys(firstNameData);
        WaitUtils.waitForElement(lastNameElement, 10).sendKeys(lastNameData);
        WaitUtils.waitForElement(birthdateElement, 10).sendKeys(birthdateDate);
        WaitUtils.waitForElement(emailElement, 10).sendKeys(emailData);
        WaitUtils.waitForElement(phoneElement, 10).sendKeys(phoneData);
        WaitUtils.waitForElement(street1Element, 10).sendKeys(street1Data);
        WaitUtils.waitForElement(street2Element, 10).sendKeys(street2Data);
        WaitUtils.waitForElement(cityElement, 10).sendKeys(cityData);
        WaitUtils.waitForElement(stateProvinceElement, 10).sendKeys(stateProvinceData);
        WaitUtils.waitForElement(postalCodeElement, 10).sendKeys(postalCodeData);
        WaitUtils.waitForElement(countryElement, 10).sendKeys(countryData);
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
