package pages;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.WaitUtils;

public class MainPage extends BasePage {

    public MainPage() {
        super();
    }

    private final By headerElement = By.xpath("/html/body/h1");
    private final By emailElement = By.xpath("//*[@id='email']");
    private final By passwordElement = By.xpath("//*[@id='password']");
    private final By signUpButtonElement = By.xpath("//*[@id='signup']");

    public void assertHeadText() {
        WebElement header = WaitUtils.waitForElement(headerElement, 10);
        Assertions.assertThat(header.getText()).contains("Contact List App");
    }

    public void loginUser(String userEmail, String userPassword) {
        WebElement loginEmail = WaitUtils.waitForElement(emailElement, 10);
        WebElement loginPassword = WaitUtils.waitForElement(passwordElement, 10);
        loginEmail.sendKeys(userEmail);
        loginPassword.sendKeys(userPassword);
        clickSubmitButton();
    }

    public void signUpButton() {
        WebElement signUpButton = WaitUtils.waitForElement(signUpButtonElement, 10);
        signUpButton.click();
    }

}
