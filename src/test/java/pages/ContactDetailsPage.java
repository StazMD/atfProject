package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.WaitUtils;

public class ContactDetailsPage extends BasePage {
    private static final Logger log = LogManager.getLogger(ContactDetailsPage.class);

    @FindBy(xpath = "//button[@id='delete']")
    private WebElement deleteContactButton;
    
    public ContactDetailsPage() {
        super();
    }

    public void deleteContactAction() {
        WaitUtils.waitForButton(deleteContactButton).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }
}
