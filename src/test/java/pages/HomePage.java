package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.WaitUtils;

public class HomePage extends BasePage {

    @FindBy(xpath = "//input[@id='emaail']")
    private WebElement emailElement;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordElement;

    @FindBy(xpath = "//button[@id='signup']")
    private WebElement signUpButtonElement;

    public void loginUser(String userEmail, String userPassword) {
        WaitUtils.waitForElement(emailElement).sendKeys(userEmail);
        WaitUtils.waitForElement(passwordElement).sendKeys(userPassword);
        submitButton();
    }

    public void openSignUpPage() {
        WaitUtils.waitForButton(signUpButtonElement).click();
    }
}
