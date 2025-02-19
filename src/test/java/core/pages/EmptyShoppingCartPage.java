package core.pages;

import core.elements.CheckPageFiltersElements;
import core.elements.EmptyShoppingCartElements;
import core.globals.Globals;
import core.utilities.BaseInformation;
import core.utilities.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EmptyShoppingCartPage {

    EmptyShoppingCartElements elements = new EmptyShoppingCartElements();
    WebDriver driver = BaseInformation.getDriver();

    public EmptyShoppingCartPage() {
        PageFactory.initElements(driver, elements);
    }

    public void removeTheSecondShoppingItem() {

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(driver -> !elements.totalShoppingCounter.getText().trim().isEmpty());
        Globals.shoppingCartCount = Integer.parseInt(elements.totalShoppingCounter.getText());

        WebElement removeButton = elements.deleteButton2;
        removeButton.click();
        WaitUtils.waitFor(1500);
    }

    public void verifyThatTheNumberOfTheItemsDecreases() {
        WebElement totalShoppingCounter = elements.totalShoppingCounter;
        Integer totalShoppingCounterInt;
        String counterText = totalShoppingCounter.getText().trim();
        if (counterText.isEmpty()) {
            totalShoppingCounterInt = 0;
        } else {
            totalShoppingCounterInt = Integer.parseInt(counterText);
        }
        assert Globals.shoppingCartCount > totalShoppingCounterInt
                : "The shopping counter has not decreased. Previous count: " + Globals.shoppingCartCount
                + ", Current count: " + totalShoppingCounterInt;

        Globals.shoppingCartCount = totalShoppingCounterInt;
    }

    public void removeTheFirstShoppingItem() {

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(driver -> !elements.totalShoppingCounter.getText().trim().isEmpty());
        Globals.shoppingCartCount = Integer.parseInt(elements.totalShoppingCounter.getText());


        WebElement removeButton = elements.deleteButton1;
        removeButton.click();
        WaitUtils.waitFor(1500);
    }

    public void verifyThatTheEmptyShoppingCardIsDisplayed() {
        WebElement emptyShoppingCard = elements.emptyShoppingCart;
        String shoppingCardEmpty = "You have no items in your shopping cart.";
        String actualText = emptyShoppingCard.getText();
        assert actualText.equals(shoppingCardEmpty)
                : "The empty shopping card is not displayed. Expected: '" + shoppingCardEmpty + "', but was: '" + actualText + "'";

        WaitUtils.waitFor(1500);
    }

    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }


    public void getUrl(String url) {
        BaseInformation.getDriver().get(url);
    }

}
