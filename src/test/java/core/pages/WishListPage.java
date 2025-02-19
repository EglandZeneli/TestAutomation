package core.pages;

import com.github.dockerjava.api.exception.ConflictException;
import core.elements.WishListElements;
import core.globals.Globals;
import core.utilities.BaseInformation;
import core.utilities.BasePageObject;
import core.utilities.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class WishListPage {

    BasePageObject basePageObject = new BasePageObject(BaseInformation.getBaseInformation());
    WishListElements elements = new WishListElements();
    WebDriver driver = BaseInformation.getDriver();

    public WishListPage() {
        PageFactory.initElements(driver, elements);
    }



    public void removePriceFilter() {
        WaitUtils.waitFor(1500);
        WebElement removeBtn = basePageObject.getWaitUtils().waitForElementClickable(elements.removePriceButton);
        removeBtn.click();
        WaitUtils.waitFor(1500);
        basePageObject.getWaitUtils().waitForElementVisible(elements.totalAmountValue);
    }
    public void checkThatCountIsIncreased() {
        String totalAmountText = elements.totalAmountValue.getText().trim();
        int total = Integer.parseInt(totalAmountText);
            int cardsCount = Globals.cardsCount;

            if (total <= cardsCount) {
                throw new IllegalStateException("Total amount (" + total + ") is not greater than cards count (" + cardsCount + ")");
            }

        WaitUtils.waitFor(1500);
    }

    public void clickOnProductItemAndWait(int productNr) {

        WebElement productItem = elements.productItems.get(productNr);
        productItem.click();
        WaitUtils.waitFor(1500);
    }

    public void clickOnAddToWishListButtonAndWait() {

        WebElement addToWishListButton = elements.addToWishListButton;
        addToWishListButton.click();
        WaitUtils.waitFor(1500);
    }



    public void isProductSuccessfulWishListMessageDisplayed(int productNr) {
        WebElement productName;

        if (productNr == 0) {
            productName = elements.firstProduct;
        } else if (productNr == 1) {
            productName = elements.secondProduct;
        } else {
            assert false : "Only two products can be verified.";
            return;
        }

        String productNameText = productName.getText();
        String expectedMessage = productNameText + " has been added to your Wish List.";
        String actualMessage = elements.successfulWishListMessage.getText();

        assert actualMessage.contains(expectedMessage)
                : "Message does not contain expected text. Actual: '" + actualMessage + "', Expected to contain: '" + expectedMessage + "'";
    }


    public void clickRedOptionToJackets() {
        WebElement redOption = elements.redOption;
        redOption.click();
        WaitUtils.waitFor(1500);
    }

    public void verifyTwoItemsAreDisplayed(int expectedWishListCounter) {

        WebElement accDropdown = elements.dropDownAccountButton;
        WaitUtils.waitFor(1500);
        basePageObject.getWaitUtils().waitForElementClickable(accDropdown).click();

        String wishListCounterText = elements.wishListCounter.getText();
        String numericPart = wishListCounterText.replaceAll("[^0-9]", "");
        int actualWishListCounter = Integer.parseInt(numericPart);

        Assert.assertEquals(actualWishListCounter, expectedWishListCounter,
                "Expected wishlist counter to be " + expectedWishListCounter + " but found " + actualWishListCounter);
    }


    public void getUrl(String url) {
        BaseInformation.getDriver().get(url);
    }

}

