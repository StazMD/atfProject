package pages;

import config.PropertyReader;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.WaitUtils;

public class MainPage extends BasePage {

    String browserUrl = PropertyReader.getProperty("url");

    @FindBy(xpath = "/html/body/h1")
    private WebElement header;

    @FindBy(xpath = "//*[@id='email']")
    private WebElement email;

    @FindBy(xpath = "//*[@id='password']")
    private WebElement password;

    @FindBy(xpath = "//*[@id='signup']")
    private WebElement signUpButton;

    public MainPage() {
        super();
    }

    public void loginUser(String userEmail, String userPassword) {
        WaitUtils.waitForElement(email, 10).sendKeys(userEmail);
        WaitUtils.waitForElement(password, 10).sendKeys(userPassword);
        clickSubmitButton();
    }

    public void clickSignUpButton() {
        WaitUtils.waitForElement(signUpButton, 10).click();
    }
}
