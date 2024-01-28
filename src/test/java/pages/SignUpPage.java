package pages;

import api.UserData;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.TestDataGeneratorUtils;
import utils.WaitUtils;

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

    public void assertSignUpPage() {
        WebElement signUpHeader = WaitUtils.waitForElement(signUpHeadElement, 10);
        Assertions.assertThat(signUpHeader.getText()).contains("Add User");
    }

    public UserData generateValidUserData() {
        String firstName = TestDataGeneratorUtils.getRandomFirstName();
        String lastName = TestDataGeneratorUtils.getRandomLastName();
        String email = TestDataGeneratorUtils.getRandomEmail();
        String password = TestDataGeneratorUtils.getRandomPassword();

        return new UserData(firstName, lastName, email, password);
    }

    public void fillUserData(UserData userData) {
        sendKeysUtils(firstNameElement, userData.getFirstName());
        sendKeysUtils(lastNameElement, userData.getLastName());
        sendKeysUtils(emailElement, userData.getEmail());
        sendKeysUtils(passwordElement, userData.getPassword());

        clickSubmitButton();
    }

    public UserData generateInvalidData(boolean invalidFirstName, boolean invalidLastName, boolean invalidEmail, boolean invalidPassword) {
        String firstName = invalidFirstName
                ? TestDataGeneratorUtils.getNegativeRandomFirstName()
                : TestDataGeneratorUtils.getRandomFirstName();
        String lastName = invalidLastName
                ? TestDataGeneratorUtils.getNegativeRandomLastName()
                : TestDataGeneratorUtils.getRandomLastName();
        String email = invalidEmail
                ? TestDataGeneratorUtils.getNegativeRandomEmail()
                : TestDataGeneratorUtils.getRandomEmail();
        String password = invalidPassword
                ? TestDataGeneratorUtils.getNegativeRandomPassword()
                : TestDataGeneratorUtils.getRandomPassword();

        return new UserData(firstName, lastName, email, password);
    }

    public void errorAlert() {
        WebElement errorAlert = WaitUtils.waitForElement(errorElement, 10);
        String actualErrorMessage = errorAlert.getText();
        log.info(actualErrorMessage);
        String expectedPattern = "User validation failed: (firstName|lastName): Path `(firstName|lastName)` \\(`.*`\\) is longer than the maximum allowed length \\(20\\).|User validation failed: email: Email is invalid|User validation failed: password: Path `password` \\(`.*`\\) is shorter than the minimum allowed length \\(7\\).";
        Assertions.assertThat(actualErrorMessage).matches(expectedPattern);
    }

}
