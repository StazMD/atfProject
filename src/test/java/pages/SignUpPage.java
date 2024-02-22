package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.WaitUtils;

public class SignUpPage extends BasePage {

    private static final Logger log = LogManager.getLogger(ContactDetailsPage.class);

    @FindBy(xpath = "//h1[text()='Add User']")
    private WebElement signUpPageHeader;

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
        WaitUtils.waitForElement(firstNameElement).sendKeys(firstName);
        WaitUtils.waitForElement(lastNameElement).sendKeys(lastName);
        WaitUtils.waitForElement(emailElement).sendKeys(email);
        WaitUtils.waitForElement(passwordElement).sendKeys(password);

        submitButton();
    }

    public String errorText() {
        return WaitUtils.waitForElement(errorElement).getText();
    }

    @Override
    public WebElement getHeaderElement() {
        return signUpPageHeader;
    }

}
