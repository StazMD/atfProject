package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import steps.common.WebDriverFactory;
import utils.WebDriverHelper;

public abstract class BasePage {

    protected WebDriver driver;
    protected WebDriverHelper webDriverHelper;
    protected Logger log;

    public BasePage() {
        this.driver = WebDriverFactory.getDriver();
        this.log = LoggerFactory.getLogger(this.getClass());
        this.webDriverHelper = new WebDriverHelper(driver);
    }

    public void clickSubmitButton() {
        By submitButtonElement = By.xpath("//*[@id='submit']");
        WebElement submitButton = driver.findElement(submitButtonElement);
        submitButton.click();
    }
}
