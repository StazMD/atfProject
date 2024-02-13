package pages;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.WaitUtils;

public class SignUpPage extends BasePage {

    public SignUpPage() {
        super();
    }

    @FindBy(id = "firstName")
    private WebElement firstName;

    @FindBy(id = "lastName")
    private WebElement lastName;

    @FindBy(id = "email")
    private WebElement email;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "error")
    private WebElement error;

    public void fillUserData() {
        WaitUtils.waitForElement(firstName, 10);
        WaitUtils.waitForElement(lastName, 10);
        WaitUtils.waitForElement(email, 10);
        WaitUtils.waitForElement(password, 10);

        clickSubmitButton();
    }

    public void errorAlert() {
        WaitUtils.waitForElement(error, 10);
        String actualErrorMessage = error.getText();
        log.info("Verifying that error message {} is presented", actualErrorMessage);
        String expectedPattern = "User validation failed: (firstName|lastName): Path `(firstName|lastName)` \\(`.*`\\) is longer than the maximum allowed length \\(20\\).|" +
                "User validation failed: email: Email is invalid|" +
                "User validation failed: password: Path `password` \\(`.*`\\) is shorter than the minimum allowed length \\(7\\).";
        Assertions.assertThat(actualErrorMessage).matches(expectedPattern);
    }

}
