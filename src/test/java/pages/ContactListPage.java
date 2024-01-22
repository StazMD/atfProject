package pages;

import config.PropertyReader;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import utils.TestDataGenerator;

import java.util.List;

public class ContactListPage extends BasePage {

    private String firstNameData;
    private String lastNameData;

    String contactListUrl = PropertyReader.getProperty("contactListPath");

    public ContactListPage() {
        super();
    }

    private final By headerElement = By.xpath("/html/body/div/header/h1");
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


    public boolean assertHeader(String headerText, boolean shouldBePresent) {
        try {
            //TODO fix assert
            webDriverHelper.findElement(headerElement);
            log.info(headerText + "is displaying");
            return shouldBePresent;
        } catch (NoSuchElementException e) {
            log.info(headerText + " is not displaying");
            return !shouldBePresent;
        }
    }


    public void addNewContactPageWithButton() {
        WebElement addContactButton = webDriverHelper.findElement(addContactButtonElement);
        addContactButton.click();
    }

    private void fillField(By locator, String value) {
        webDriverHelper.findElement(locator).sendKeys(value);
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

//    public void assertFirstLastNameElement() {
//        String firstNameLastName = firstNameData + " " + lastNameData;
//        driver.get(contactListUrl);
//        await().atMost(10, SECONDS).until(() -> driver
//                .findElement(firstLastNameElement)
//                .isDisplayed());
//        WebElement contactTable = driver.findElement(firstLastNameElement);
//        log.info(firstNameLastName);
//        Assertions.assertThat(contactTable.getText()).contains(firstNameLastName);
//    }

    public WebElement findEntry() {
        String firstNameLastName = firstNameData + " " + lastNameData;
        log.info(firstNameLastName);
        driver.get(contactListUrl);
        WebElement table = webDriverHelper.findElement(contactsTable);
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
