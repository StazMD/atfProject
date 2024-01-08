package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import utils.TestDataGenerator;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;

public class ContactListPage extends BasePage {

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


    public void assertHeader(String headerText, boolean shouldBePresent) {
        await().atMost(10, SECONDS)
                .pollInterval(1, SECONDS)
                .until(() -> {
                    try {
                        driver.findElement(headerElement);
                        log.info("Header of the Contact List is displaying");
                        return shouldBePresent;
                    } catch (NoSuchElementException e) {
                        log.info("Header of the Contact List is not displaying");
                        return !shouldBePresent;
                    }
                });
    }

    public void AddNewContactButton() {
        WebElement addContactButton = driver.findElement(addContactButtonElement);
        addContactButton.click();
    }

    public void populatingContactFields() {
        String firstName = driver.findElement(firstNameElement);
        sendKeys(firstName, TestDataGenerator.getRandomFirstName());
        driver.findElement(lastNameElement).sendKeys(TestDataGenerator.getRandomLastName());
        driver.findElement(birthdateElement).sendKeys(TestDataGenerator.getRandomDate());
        driver.findElement(emailElement).sendKeys(TestDataGenerator.getRandomEmail());
        driver.findElement(phoneElement).sendKeys(TestDataGenerator.getRandomPhoneNumber());
        driver.findElement(street1Element).sendKeys(TestDataGenerator.getRandomStreetAddress());
        driver.findElement(street2Element).sendKeys(TestDataGenerator.getRandomStreetAddress());
        driver.findElement(cityElement).sendKeys(TestDataGenerator.getRandomCity());
        driver.findElement(stateProvinceElement).sendKeys(TestDataGenerator.getState());
        driver.findElement(postalCodeElement).sendKeys(TestDataGenerator.getPostalCode());
        driver.findElement(countryElement).sendKeys(TestDataGenerator.getCountry());
        clickSubmitButton();
    }

    private final String firstNameLastName = firstNameElement + lastNameElement;

    public void assertContactListTable(boolean shouldBePresent) {

        await().atMost(10, SECONDS)
                .pollInterval(1, SECONDS)
                .until(() -> {
                    try {
                        driver.findElement(headerElement);
                        log.info("Header of the Contact List is displaying");
                        return shouldBePresent;
                    } catch (NoSuchElementException e) {
                        log.info("Header of the Contact List is not displaying");
                        return !shouldBePresent;
                    }
                });
    }


}
