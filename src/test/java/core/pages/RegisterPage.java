package core.pages;


import core.elements.RegisterPageElements;
import core.globals.Globals;
import core.utilities.BaseInformation;
import core.utilities.BasePageObject;
import core.utilities.WaitUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
    BasePageObject basePageObject = new BasePageObject(BaseInformation.getBaseInformation());
    RegisterPageElements registerPageElements =  new RegisterPageElements();

    public RegisterPage(){
        PageFactory.initElements(BaseInformation.getDriver(), this);
    }

    public void getUrl(String url){
        BaseInformation.getDriver().get(url);
    }
    public void clickRegisterButton(){
        basePageObject
                .getWaitUtils()
                .waitForElementClickable(registerPageElements.registerButton)
                .click();
        WaitUtils.waitFor(1500);
    }

    public void setFirstName(String firstName){
        Globals.firstName = firstName;
        basePageObject
                .getWaitUtils()
                .waitForElementVisible(registerPageElements.firstNameInput)
                .sendKeys(firstName);
        WaitUtils.waitFor(1500);
    }

    public void setLastName(String lastName){
        Globals.lastName = lastName;
        basePageObject
                .getWebElementUtils()
                .sendKeysToElementWithWait(registerPageElements.lastNameInput,lastName,2);
        WaitUtils.waitFor(1500);
    }

    public void setEmail(String emailPrefix){
        String generatedEmail = emailPrefix + "@gmail.com";
        basePageObject
                .getWaitUtils()
                .waitForElementVisible(registerPageElements.emailInput)
                .sendKeys(generatedEmail);
        WaitUtils.waitFor(1500);
        Globals.email = generatedEmail;
    }

    public void setPassword(String securePass123){
        registerPageElements.password.sendKeys(Globals.password);
        WaitUtils.waitFor(1500);

    }

    public void setConfirmPassword(String securePass123){
        registerPageElements.confirmPassword.sendKeys(Globals.confirmPassword);
    }

    public void clickRegisterButtonForm(){
        basePageObject
                .getWaitUtils()
                .waitForElementClickable(registerPageElements.registerButtonForm)
                .click();
        WaitUtils.waitFor(1500);
    }

    public boolean checkRegister(){
        return registerPageElements.verifyRegistration.isDisplayed();
    }

    public boolean isSuccessMessageDisplayed(){
        return registerPageElements.successMessageText.isDisplayed() &&
                registerPageElements.successMessageIcon.isDisplayed();
    }

    public void signOut() {

        WebElement accDropdown = registerPageElements.dropDownAccountButton;
        WaitUtils.waitFor(1500);
        basePageObject.getWaitUtils().waitForElementClickable(accDropdown).click();
        WaitUtils.waitFor(1500);
        WebElement signOut = basePageObject.getWaitUtils().waitForElementClickable(registerPageElements.signOutButton);
        signOut.click();
    }

    public void openRegistrationPage(String baseUrl) {
        BaseInformation.getDriver().get(baseUrl + "/register");
        WaitUtils.waitForPageToLoad();
    }

}
