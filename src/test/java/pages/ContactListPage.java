package pages;

import config.PropertyReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.TestDataGenerator;
import utils.WaitUtils;

import java.util.List;

public class ContactListPage extends BasePage {

    private String firstNameData;
    private String lastNameData;
    private final String contactListUrl = PropertyReader.getProperty("contactListPath");

    public ContactListPage() {
        super();
    }

    private final By addContactButtonElement = By.xpath("//*[@id='add-contact']");

    private final By firstNameElement = By.xpath("//*[@id='firstName']");
    private final By lastNameElement = By.xpath("//*[@id='lastName']");
    private final By birthdateElement = By.xpath("//*[@id='birthdate']");
    private final By emailElement = By.xpath("//*[@id='email']");
    private final By phoneElement = By.xpath("//*[@id='phone']");
    private final By street1Element = By.xpath("//*[@id='street1']");
    private final By street2Element = By.xpath("//*[@id='street2']");
    private final By cityElement = By.xpath("//*[@id='city']");
    private final By stateProvinceElement = By.xpath("//*[@id='stateProvince']");
    private final By postalCodeElement = By.xpath("//*[@id='postalCode']");
    private final By countryElement = By.xpath("//*[@id='country']");

    private final By contactsTable = By.xpath("//*[@id='myTable']");
    private final By contactTableBodyRowElement = By.className("contactTableBodyRow");

    public void addNewContactPageWithButton() {
        WebElement addContactButton = WaitUtils.waitForElement(addContactButtonElement, 10);
        addContactButton.click();
    }

    private void fillField(By locator, String value) {
        WaitUtils.waitForElement(locator, 10).sendKeys(value);
    }

    public void fillingContactFields() {
        firstNameData = TestDataGenerator.getRandomFirstName();
        fillField(firstNameElement, firstNameData);
        lastNameData = TestDataGenerator.getRandomLastName();
        fillField(lastNameElement, lastNameData);
        fillField(birthdateElement, TestDataGenerator.getRandomDate());
        fillField(emailElement, TestDataGenerator.getRandomEmail());
        fillField(phoneElement, TestDataGenerator.getRandomPhoneNumber());
        fillField(street1Element, TestDataGenerator.getRandomStreetAddress());
        fillField(street2Element, TestDataGenerator.getRandomStreetAddress());
        fillField(cityElement, TestDataGenerator.getRandomCity());
        fillField(stateProvinceElement, TestDataGenerator.getState());
        fillField(postalCodeElement, TestDataGenerator.getPostalCode());
        fillField(countryElement, TestDataGenerator.getCountry());
        clickSubmitButton();
    }

    public WebElement findEntry() {
        driver.get(contactListUrl);
        String firstNameLastName = firstNameData + " " + lastNameData;
        WebElement table = WaitUtils.waitForElement(contactsTable, 10);
        List<WebElement> rows = table.findElements(contactTableBodyRowElement);
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            for (WebElement cell : cells) {
                if (cell.getText().equals(firstNameLastName)) {
                    log.info("Entry found: " + firstNameLastName);
                    return cell;
                }
            }
        }
        return null;
    }

    public void getContactDetails() {
        WebElement contact = findEntry();
        if (contact != null) {
            contact.click();
        } else {
            log.info("Entry to click on was not found.");
        }
    }
}
