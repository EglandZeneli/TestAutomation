package core.elements;

import core.utilities.BaseInformation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInPageElements {
    public SignInPageElements(){
        PageFactory.initElements(BaseInformation.getDriver(), this);
    }

    @FindBy(xpath = "//li[@class=\"authorization-link\"]/a")
    public WebElement signInLink;

    @FindBy(xpath = "//input[@id='email']")
    public WebElement emailInput;

    @FindBy(xpath = "//input[@id='pass']")
    public WebElement passwordInput;

    @FindBy(xpath = "//button[@id='send2']")
    public WebElement signInButton;

    @FindBy(xpath = "//span[@class='logged-in']")
    public WebElement welcomeMessage;

    @FindBy(xpath = "//span[@class=\"customer-name\"]")
    public WebElement dropDownAccountButton;

    @FindBy(xpath = "//li[@class=\"authorization-link\"]/a")
    public WebElement signOutButton;
}
