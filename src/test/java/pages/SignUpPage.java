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

    public void userFields(UserEntity user) {
        WaitUtils.sendKeysUtil(firstNameElement, user.getFirstName());
        WaitUtils.sendKeysUtil(lastNameElement, user.getLastName());
        WaitUtils.sendKeysUtil(emailElement, user.getEmail());
        WaitUtils.sendKeysUtil(passwordElement, user.getPassword());
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
