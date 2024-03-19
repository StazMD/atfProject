package pages;

import entity.ContactEntity;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.WaitUtils;

import java.util.List;

public class ContactListPage extends BasePage {

    @FindBy(xpath = "//h1")
    private WebElement contactListPageHeader;

    @FindBy(xpath = "//button[@id='add-contact']")
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

    public void openAddContactPage() {
        WaitUtils.waitForButton(addContactButton).click();
    }

    public void fillContactFields(ContactEntity contactEntity) {
        WaitUtils.waitForElement(firstNameElement).sendKeys(contactEntity.getFirstName());
        WaitUtils.waitForElement(lastNameElement).sendKeys(contactEntity.getLastName());
        WaitUtils.waitForElement(birthdateElement).sendKeys(contactEntity.getBirthdate());
        WaitUtils.waitForElement(emailElement).sendKeys(contactEntity.getEmail());
        WaitUtils.waitForElement(phoneElement).sendKeys(contactEntity.getPhone());
        WaitUtils.waitForElement(street1Element).sendKeys(contactEntity.getStreet1());
        WaitUtils.waitForElement(street2Element).sendKeys(contactEntity.getStreet2());
        WaitUtils.waitForElement(cityElement).sendKeys(contactEntity.getCity());
        WaitUtils.waitForElement(stateProvinceElement).sendKeys(contactEntity.getStateProvince());
        WaitUtils.waitForElement(postalCodeElement).sendKeys(contactEntity.getPostalCode());
        WaitUtils.waitForElement(countryElement).sendKeys(contactEntity.getCountry());
        clickSubmitButton();
    }

    public void getContactFromTable(String firstNameLastName) {
        WebElement singleContact = driver.findElement(By.xpath("//td[contains(text(), '" + firstNameLastName + "')]"));
        WaitUtils.waitForElement(singleContact).click();
    }

    public List<WebElement> getRowsFromTable() {
        WaitUtils.waitForElement(contactsTableElement);
        return contactTableBodyRowElements;
    }

    @Override
    public WebElement getHeaderElement() {
        return contactListPageHeader;
    }
}
