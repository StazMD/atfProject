package pages;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.TestDataGenerator;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;

public class SignUpPage extends BasePage {

    public SignUpPage() {
        super();
    }

    private final By signUpHeadElement = By.xpath("/html/body/div/h1");

    private final By firstNameElement = By.xpath("//*[@id='firstName']");
    private final By lastNameElement = By.xpath("//*[@id='lastName']");
    private final By emailElement = By.xpath("//*[@id='email']");
    private final By passwordElement = By.xpath("//*[@id='password']");

    private final By errorElement = By.xpath("//*[@id='error']");

    public void assertSignAddPage() {
        WebElement signUpHeader = driver.findElement(signUpHeadElement);
        Assertions.assertThat(signUpHeader.getText()).contains("Add User");
    }

    public void populateUserFieldsWithData(boolean firstNameField, boolean positiveLastName, boolean positiveEmail, boolean positivePassword) {
        String firstName = firstNameField
                ? TestDataGenerator.getRandomFirstName()
                : TestDataGenerator.getNegativeRandomFirstName();
        String lastName = positiveLastName
                ? TestDataGenerator.getRandomLastName()
                : TestDataGenerator.getNegativeRandomLastName();
        String email = positiveEmail
                ? TestDataGenerator.getRandomEmail()
                : TestDataGenerator.getNegativeRandomEmail();
        String password = positivePassword
                ? TestDataGenerator.getRandomPassword()
                : TestDataGenerator.getNegativeRandomPassword();

        sendKeys(firstNameElement, firstName);
        sendKeys(lastNameElement, lastName);
        sendKeys(emailElement, email);
        sendKeys(passwordElement, password);
        clickSubmitButton();
    }

    private void sendKeys(By locator, String keysToSend) {
        WebElement element = driver.findElement(locator);
        log.info("Field [" + locator + "]: " + keysToSend);
        element.sendKeys(keysToSend);
    }

    public void errorAlert() {
        WebElement errorAlert = driver.findElement(errorElement);
        await().atMost(10, SECONDS)
                .until(errorAlert::isDisplayed);
        String actualErrorMessage = errorAlert.getText();
        log.info(actualErrorMessage);
        String expectedPattern = "User validation failed: (firstName|lastName): Path `(firstName|lastName)` \\(`.*`\\) is longer than the maximum allowed length \\(20\\).|User validation failed: email: Email is invalid|User validation failed: password: Path `password` \\(`.*`\\) is shorter than the minimum allowed length \\(7\\).";
        Assertions.assertThat(actualErrorMessage).matches(expectedPattern);
    }

}
