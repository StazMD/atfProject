package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

public class ContactDetailsPage extends BasePage {

    public ContactDetailsPage() {
        super();
    }

    private final By deleteContactButtonElement = By.xpath("//*[@id='delete']");

    public void deleteAction() {
        webDriverHelper.findElement(deleteContactButtonElement).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

}
