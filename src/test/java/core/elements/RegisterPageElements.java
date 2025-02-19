package core.elements;

import core.utilities.BaseInformation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPageElements {

    public RegisterPageElements() {
        PageFactory.initElements(BaseInformation.getDriver(), this);
    }


    @FindBy(xpath = "//a[contains(@href, '/customer/account/create')]")
    public WebElement registerButton;

    @FindBy(xpath = "//button[@title='Create an Account']")
    public WebElement registerButtonForm;

    @FindBy(xpath = "//input[@id='firstname']")
    public WebElement firstNameInput;

    @FindBy(xpath = "//input[@id='lastname']")
    public WebElement lastNameInput;

    @FindBy(xpath = "//input[@id='email_address']")
    public WebElement emailInput;

    @FindBy(xpath = "//input[@id='password']")
    public WebElement password;

    @FindBy(xpath = "//input[@id='password-confirmation']")
    public WebElement confirmPassword;

    @FindBy(xpath = "//div[contains(@class, 'message-success')]")
    public WebElement verifyRegistration;

    @FindBy(xpath = "//div[@class='message-success success message']/div")
    public WebElement successMessageText;

    @FindBy(xpath = "//div[@class='message-success success message']/div")
    public WebElement successMessageIcon;

    @FindBy(xpath = "//span[@class=\"customer-name\"]")
    public WebElement dropDownAccountButton;

    @FindBy(xpath = "//li[@class=\"authorization-link\"]/a")
    public WebElement signOutButton;

}
