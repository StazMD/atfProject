package pages;

import entity.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.WaitUtils;

import java.util.List;

public class ContactListPage extends BasePage {

    //TODO why?
    @FindBy(xpath = "//*[@id='add-contact']")
    private WebElement addContactButton;

    @FindBy(xpath = "//*[@id='firstName']")
    private WebElement firstNameElement;

    @FindBy(xpath = "//*[@id='lastName']")
    private WebElement lastNameElement;

    @FindBy(xpath = "//*[@id='birthdate']")
    private WebElement birthdateElement;

    @FindBy(xpath = "//*[@id='email']")
    private WebElement emailElement;

    @FindBy(xpath = "//*[@id='phone']")
    private WebElement phoneElement;

    @FindBy(xpath = "//*[@id='street1']")
    private WebElement street1Element;

    @FindBy(xpath = "//*[@id='street2']")
    private WebElement street2Element;

    @FindBy(xpath = "//*[@id='city']")
    private WebElement cityElement;

    @FindBy(xpath = "//*[@id='stateProvince']")
    private WebElement stateProvinceElement;

    @FindBy(xpath = "//*[@id='postalCode']")
    private WebElement postalCodeElement;

    @FindBy(xpath = "//*[@id='country']")
    private WebElement countryElement;

    @FindBy(xpath = "//*[@id='myTable']")
    public WebElement contactsTableElement;

    @FindBy(className = "contactTableBodyRow")
    private List<WebElement> contactTableBodyRowElements;

    public void addNewContactButton() {
        WaitUtils.waitForElement(addContactButton).click();
    }

    public void contactFields(Contact contact) {
        WaitUtils.waitForElement(firstNameElement).sendKeys(contact.getFirstName());
        WaitUtils.waitForElement(lastNameElement).sendKeys(contact.getLastName());
        WaitUtils.waitForElement(birthdateElement).sendKeys(contact.getBirthdate());
        WaitUtils.waitForElement(emailElement).sendKeys(contact.getEmail());
        WaitUtils.waitForElement(phoneElement).sendKeys(contact.getPhone());
        WaitUtils.waitForElement(street1Element).sendKeys(contact.getStreet1());
        WaitUtils.waitForElement(street2Element).sendKeys(contact.getStreet2());
        WaitUtils.waitForElement(cityElement).sendKeys(contact.getCity());
        WaitUtils.waitForElement(stateProvinceElement).sendKeys(contact.getStateProvince());
        WaitUtils.waitForElement(postalCodeElement).sendKeys(contact.getPostalCode());
        WaitUtils.waitForElement(countryElement).sendKeys(contact.getCountry());

        submitButton();
    }

    public void getContactFromTable(String firstNameLastName) {
        WebElement singleContact = driver.findElement(By.xpath("//td[contains(text(), '" + firstNameLastName + "')]"));
        WaitUtils.waitForElement(singleContact).click();
    }

    public List<WebElement> getRowsFromTable() {
        WaitUtils.waitForElement(contactsTableElement);
        return contactTableBodyRowElements;
    }
}
