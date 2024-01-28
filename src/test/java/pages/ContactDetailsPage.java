package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ContactDetailsPage extends BasePage {

    @FindBy(xpath = "//button[@id='delete']")
    private WebElement deleteContactButton;

    public ContactDetailsPage() {
        super();
    }

    public void deleteContactButton() {
        deleteContactButton.click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

}
