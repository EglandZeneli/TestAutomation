package core.pages;

import core.elements.SignInPageElements;
import core.globals.Globals;
import core.utilities.BaseInformation;
import core.utilities.BasePageObject;
import core.utilities.WaitUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.io.Console;

public class SignInPage {
    BasePageObject basePageObject = new BasePageObject(BaseInformation.getBaseInformation());
    SignInPageElements signInPageElements = new SignInPageElements();

    public SignInPage(){
        PageFactory.initElements(BaseInformation.getDriver(), this);
    }

    public void getUrl(String url){
        BaseInformation.getDriver().get(url);
    }
    public void clickSignInLink(){
        basePageObject
                .getWaitUtils()
                .waitForElementClickable(signInPageElements.signInLink)
                .click();
        WaitUtils.waitFor(1500);
    }

    public void setEmail(String email){
        basePageObject
                .getWaitUtils()
                .waitForElementVisible(signInPageElements.emailInput)
                .sendKeys(Globals.email);
        WaitUtils.waitFor(1500);
    }

    public void setPassword(String password){
        basePageObject
                .getWaitUtils()
                .waitForElementVisible(signInPageElements.passwordInput)
                .sendKeys(Globals.password);
        WaitUtils.waitFor(1500);
    }

    public void clickSignInButton(){
        basePageObject
                .getWaitUtils()
                .waitForElementClickable(signInPageElements.signInButton)
                .click();
        WaitUtils.waitFor(1500);
    }

    public String getWelcomeMessageText() {
        return basePageObject
                .getWaitUtils()
                .waitForElementVisible(signInPageElements.welcomeMessage)
                .getText();
    }
    public void checkEqualityOfMessages(String message1, String message2) {
;
        if(!message1.equals(message2)){
            throw new AssertionError(message1 + " not equal to " + message2);
        }
    }


    public void clickSignOut(){
        WebElement accDropdown = signInPageElements.dropDownAccountButton;
        WaitUtils.waitFor(1500);
        basePageObject.getWaitUtils().waitForElementClickable(accDropdown).click();
        WebElement signOut = basePageObject.getWaitUtils().waitForElementClickable(signInPageElements.signOutButton);
        WaitUtils.waitFor(1500);
        signOut.click();
    }

    public void openSignInPage(String baseUrl) {
        BaseInformation.getDriver().get(baseUrl + "/signin");
        WaitUtils.waitForPageToLoad();
    }

}
