package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.WaitUtils;

public class HomePage extends BasePage {

    @FindBy(xpath = "//*[@id='email']")
    private WebElement emailElement;

    @FindBy(xpath = "//*[@id='password']")
    private WebElement passwordElement;

    @FindBy(xpath = "//*[@id='signup']")
    private WebElement signUpButtonElement;

    public void loginUser(String userEmail, String userPassword) {
        WaitUtils.waitForElement(emailElement).sendKeys(userEmail);
        WaitUtils.waitForElement(passwordElement).sendKeys(userPassword);
        submitButton();
    }

    public void clickSignUpButton() {
        WaitUtils.waitForElement(signUpButtonElement).click();
    }
}
