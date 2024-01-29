package pages;

import config.PropertyReader;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
        email.sendKeys(userEmail);
        password.sendKeys(userPassword);
        clickSubmitButton();
    }

    public void clickSignUpButton() {
        signUpButton.click();
    }
}
