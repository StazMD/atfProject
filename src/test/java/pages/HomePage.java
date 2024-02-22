package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.WaitUtils;

public class HomePage extends BasePage {

    private static final Logger log = LogManager.getLogger(HomePage.class);

    @FindBy(xpath = "//input[@id='email']")
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
