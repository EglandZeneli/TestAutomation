package core.pages;

import core.elements.CheckPageFiltersElements;
import core.globals.Globals;
import core.utilities.BaseInformation;
import core.utilities.BasePageObject;
import core.utilities.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class CheckPageFiltersPage {

    BasePageObject basePageObject = new BasePageObject(BaseInformation.getBaseInformation());
    CheckPageFiltersElements elements = new CheckPageFiltersElements();
    WebDriver driver = BaseInformation.getDriver();

    public CheckPageFiltersPage() {
        PageFactory.initElements(driver, elements);
    }

    public void navigateToJacketsPage() {
        Actions actions = new Actions(driver);

        WebElement women = basePageObject.getWaitUtils().waitForElementClickable(elements.womenDropdown);
        actions.moveToElement(women).perform();

        WebElement tops = basePageObject.getWaitUtils().waitForElementClickable(elements.topsDropdown);
        actions.moveToElement(tops).perform();
        WaitUtils.waitFor(1500);
        // Wait for "Jackets" to be clickable and then click it
        WebElement jackets = basePageObject.getWaitUtils().waitForElementClickable(elements.jacketsDropdown);
        jackets.click();
    }



    public void selectRedColorFilter() {

        WebElement colorPanel = basePageObject.getWaitUtils().waitForElementClickable(elements.colorPanel);
        WaitUtils.waitFor(1500);
        colorPanel.click();

        WebElement redSwatch = basePageObject.getWaitUtils().waitForElementClickable(elements.redColorJackets);
        WaitUtils.waitFor(1500);
        redSwatch.click();
        WaitUtils.waitFor(3000);

        basePageObject.getWaitUtils().waitForElementVisible(elements.productItems.get(0));
    }


    public void verifyAllProductsHaveRedColorSelected() {
        WebElement totalCount = elements.totalCount;
        int totalCountValue = Integer.parseInt(totalCount.getText().trim());
        List<WebElement> redSwatches = elements.redSwatches;
        WaitUtils.waitFor(1500);
        Assert.assertEquals(redSwatches.size(), totalCountValue, "Not all products have red color selected");
    }



    public void selectWantedPriceFilter() {
        WebElement pricePannel = basePageObject.getWaitUtils().waitForElementClickable(elements.pricePanel);
        pricePannel.click();

        WebElement wantedPriceRange = basePageObject.getWaitUtils().waitForElementClickable(elements.wantedPrice);
        WaitUtils.waitFor(1500);
        wantedPriceRange.click();
        basePageObject.getWaitUtils().waitForElementVisible(elements.productItems.get(0));
    }

    public void verifyThatOnlyTwoProductsAreDisplayed(int expectedCount) {
        WebElement totalAmount = elements.totalCount;
        int totalAmountValue = Integer.parseInt(totalAmount.getText().trim());
        Globals.cardsCount = totalAmountValue;
        WaitUtils.waitFor(3000);
        Assert.assertEquals(totalAmountValue, expectedCount, "The total number of products is not as expected.");
    }

    public void verifyAllProductPricesInRange() {
        List<WebElement> prices = elements.cardsPriceRange;

        assert prices != null && !prices.isEmpty() : "No price elements found";

        for (WebElement priceElement : prices) {
            String priceText = priceElement.getText();
            assert priceText != null && !priceText.isEmpty() : "Price text is empty for element: " + priceElement;

            priceText = priceText.replace("$", "").trim();

            double price;
            try {
                price = Double.parseDouble(priceText);
            } catch (NumberFormatException e) {
                assert false : "Unable to parse price: " + priceText;
                return;
            }

            assert price >= 50.00 && price <= 59.99
                    : "The price out of range: " + priceText;
        }
    }

    public void getUrl(String url) {
        BaseInformation.getDriver().get(url);
    }

}
