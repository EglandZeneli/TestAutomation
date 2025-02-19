package core.pages;

import core.elements.ShoppingCartTestElements;
import core.elements.SignInPageElements;
import core.utilities.BaseInformation;
import core.utilities.BasePageObject;
import core.utilities.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ShoppingCartTestPage {

    BasePageObject basePageObject = new BasePageObject(BaseInformation.getBaseInformation());
    ShoppingCartTestElements elements = new ShoppingCartTestElements();
    WebDriver driver = BaseInformation.getDriver();
    public ShoppingCartTestPage(){
        PageFactory.initElements(driver, elements);
    }

    public void clickOnProduct1Size() {
        WebElement firstProductSizeS = elements.sizeSmall1;
        firstProductSizeS.click();
        WaitUtils.waitFor(1500);
    }

    public void clickOnProduct2Size() {
        WebElement secondProductSizeS = elements.sizeSmall2;
        secondProductSizeS.click();
        WaitUtils.waitFor(1500);
    }
    public void hoverOverFirstCardAndClickAddToCart() {
        Actions actions = new Actions(driver);

        WebElement productItem1 = elements.productItem1;

        WebElement clickableCard = basePageObject.getWaitUtils()
                .waitForElementClickable(productItem1);

        actions.moveToElement(clickableCard).perform();

        WebElement addToCartButton = basePageObject.getWaitUtils().waitForElementClickable(
                productItem1.findElement(By.xpath(".//button[contains(@class, 'action tocart')]"))
        );
        addToCartButton.click();
        WaitUtils.waitFor(1500);
        }

    public void verifyFirstProductIsAddedToCart() {
        String productName1 = elements.productName1.getText().trim();
        String successfulMessageText = elements.successfulMessage1.getText().trim();

        assert successfulMessageText.equals("You added " + productName1 + " to your shopping cart.")
                : "The successful message does not contain the product name: " + productName1;
        WaitUtils.waitFor(1500);
    }





    public void hoverOverSecondCardAndClickAddToCart() {
        Actions actions = new Actions(BaseInformation.getDriver());

        WebElement productItem2 = elements.productItem2;

        WebElement clickableCard = basePageObject.getWaitUtils().waitForElementClickable(productItem2);

        actions.moveToElement(clickableCard).perform();

        WebElement addToCartButton = basePageObject.getWaitUtils().waitForElementClickable(
                productItem2.findElement(By.xpath(".//button[contains(@class, 'action tocart')]"))
        );
        addToCartButton.click();
        WaitUtils.waitFor(8000);

    }

    public void verifySecondProductIsAddedToCart() {
        String productName2 = elements.productName2.getText().trim();
        String successfulMessageText = elements.successfulMessage2.getText().trim();

        assert successfulMessageText.equals("You added " + productName2 + " to your shopping cart.")
                : "The successful message does not contain the product name: " + productName2;
        WaitUtils.waitFor(1500);
    }

    public void clickOnShoppingCartLink() {
        WebElement shoppingCartLink = elements.shoppingCartLink;
        shoppingCartLink.click();
        WaitUtils.waitFor(1500);
    }
    public void verifyThatWeHaveNavigatedToShoppingCartPage() {
        String currentUrl = driver.getCurrentUrl();
        assert currentUrl.contains("checkout/cart")
                : "Not on the shopping cart page. Current URL: " + currentUrl;
    }

    public void verifyTotalShoppingSum() {
        int totalSum = Integer.parseInt(elements.totalShoppingSum.getText().replaceAll("[^\\d]", ""));
        int firstPrice = Integer.parseInt(elements.firstProductPrice.getText().replaceAll("[^\\d]", ""));
        int secondPrice = Integer.parseInt(elements.secondProductPrice.getText().replaceAll("[^\\d]", ""));
        assert totalSum == (firstPrice + secondPrice)
                : "The total shopping sum (" + totalSum + ") does not match the expected sum ("
                + (firstPrice + secondPrice) + ").";
    }

    public void getUrl(String url) {
        BaseInformation.getDriver().get(url);
    }

}
