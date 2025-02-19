package core.elements;

import core.utilities.BaseInformation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class WishListElements {
    public WishListElements(){
        PageFactory.initElements(BaseInformation.getDriver(), this);
    }

    @FindBy(xpath = "//a[@class='action remove' and contains(@href, 'color=58')]")
    public WebElement removePriceButton;

    @FindBy(xpath = "//p[@class='toolbar-amount' and @id='toolbar-amount']//span[@class='toolbar-number']")
    public WebElement totalAmountValue;


    @FindBy(xpath = "//li[contains(@class,'product-item')]")
    public List<WebElement> productItems;

    @FindBy(xpath ="(//a[contains(@class, 'towishlist')]//span[text() = \"Add to Wish List\"])[1]")
    public WebElement addToWishListButton;

    @FindBy(xpath ="(//div[@class=\"products-grid wishlist\"]//ol[@class=\"product-items\"]//li[contains(@class, \"product-item\")]//strong[@class=\"product-item-name\"]/a)[1]")
    public WebElement firstProduct;

    @FindBy(xpath = "(//div[@class=\"products-grid wishlist\"]//ol[@class=\"product-items\"]//li[contains(@class, \"product-item\")]//strong[@class=\"product-item-name\"]/a)[2]")
    public WebElement secondProduct;

    @FindBy(xpath = "//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']")
    public WebElement successfulWishListMessage;


    @FindBy(xpath = "//div[@id=\"option-label-color-93-item-58\"]")
    public WebElement redOption;

    @FindBy(xpath = "//span[@class=\"customer-name\"]")
    public WebElement dropDownAccountButton;

    @FindBy(xpath = "(//span[@class=\"counter qty\"])[1]")
    public WebElement wishListCounter;
}
