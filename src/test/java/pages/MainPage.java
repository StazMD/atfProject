package pages;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MainPage extends BasePage {

    public MainPage() {
        super();
    }

    private final By headerElement = By.xpath("/html/body/h1");
    private final By emailElement = By.xpath("//*[@id='email']");
    private final By passwordElement = By.xpath("//*[@id='password']");
    private final By signUpButtonElement = By.xpath("//*[@id='signup']");

    public void assertHeadText() {
        WebElement header = driver.findElement(headerElement);
        Assertions.assertThat(header.getText()).contains("Contact List App");
    }

    public void loginUser(String userEmail, String userPassword) {
        WebElement loginEmail = driver.findElement(emailElement);
        WebElement loginPassword = driver.findElement(passwordElement);
        loginEmail.sendKeys(userEmail);
        loginPassword.sendKeys(userPassword);
        log.info(userEmail + " " + userPassword);
        clickSubmitButton();
    }

    public void signUpButton() {
        WebElement signUpButton = driver.findElement(signUpButtonElement);
        signUpButton.click();
    }

}
