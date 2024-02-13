package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.WaitUtils;

public class SignUpPage extends BasePage {
    @FindBy(id = "firstName")
    private WebElement firstNameElement;

    @FindBy(id = "lastName")
    private WebElement lastNameElement;

    @FindBy(id = "email")
    private WebElement emailElement;

    @FindBy(id = "password")
    private WebElement passwordElement;

    @FindBy(id = "error")
    private WebElement errorElement;

    public void userFields(String firstName, String lastName, String email, String password) {
        WaitUtils.waitForElement(firstNameElement, 10).sendKeys(firstName);
        WaitUtils.waitForElement(lastNameElement, 10).sendKeys(lastName);
        WaitUtils.waitForElement(emailElement, 10).sendKeys(email);
        WaitUtils.waitForElement(passwordElement, 10).sendKeys(password);

        clickSubmitButton();
    }

    public String errorText() {
        return WaitUtils.waitForElement(errorElement, 10).getText();
    }

}
