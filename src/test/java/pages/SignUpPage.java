package pages;

import entity.UserEntity;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.WaitUtils;

public class SignUpPage extends BasePage {

    @FindBy(xpath = "//h1")
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
        clickSubmitButton();
    }

    public void userFields(UserEntity user) {
        WaitUtils.waitForElement(firstNameElement).sendKeys(user.getFirstName());
        WaitUtils.waitForElement(lastNameElement).sendKeys(user.getLastName());
        WaitUtils.waitForElement(emailElement).sendKeys(user.getEmail());
        WaitUtils.waitForElement(passwordElement).sendKeys(user.getPassword());
        clickSubmitButton();
    }

    public String errorText() {
        return WaitUtils.waitForElement(errorElement).getText();
    }

    @Override
    public WebElement getHeaderElement() {
        return signUpPageHeader;
    }
}
