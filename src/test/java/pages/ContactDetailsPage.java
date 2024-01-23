package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import utils.WaitUtils;

public class ContactDetailsPage extends BasePage {

    public ContactDetailsPage() {
        super();
    }

    private final By deleteContactButtonElement = By.xpath("//*[@id='delete']");

    public void deleteAction() {
        WaitUtils.waitForElement(deleteContactButtonElement, 10).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

}
