package core.elements;

import core.utilities.BaseInformation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ShoppingCartTestElements {

    public ShoppingCartTestElements(){
        PageFactory.initElements(BaseInformation.getDriver(), this);
    }

    @FindBy(xpath = "(//ol[contains(@class,'product-items')]/li[contains(@class,'product-item')])[1]")
    public WebElement productItem1;

    @FindBy(xpath = "(//ol[contains(@class,'product-items')]/li[contains(@class,'product-item')])[2]")
    public WebElement productItem2;

    @FindBy(xpath = "(//div[contains(@class, 'swatch-option') and text()='S'])[1]")
    public WebElement sizeSmall1;

    @FindBy(xpath = "(//div[contains(@class, 'swatch-option') and text()='S'])[2]")
    public WebElement sizeSmall2;

    @FindBy(xpath = "//div[@data-bind=\"html: $parent.prepareMessageForHtml(message.text)\"]")
    public WebElement successfulMessage1;

    @FindBy(xpath = "//div[@data-bind=\"html: $parent.prepareMessageForHtml(message.text)\"]")
    public WebElement successfulMessage2;

    @FindBy(xpath = "(//a[@class=\"product-item-link\"])[1]")
    public WebElement productName1;

    @FindBy(xpath = "(//a[@class=\"product-item-link\"])[2]")
    public WebElement productName2;

    @FindBy(xpath = "//div[@data-bind=\"html: $parent.prepareMessageForHtml(message.text)\"]/a")
    public WebElement shoppingCartLink;

    @FindBy(xpath = "//tr[@class=\"grand totals\"]//td[@class=\"amount\"]//span[@class=\"price\"]")
    public WebElement totalShoppingSum;

    @FindBy(xpath = "(//tr[@class=\"item-info\"]//td[@class=\"col subtotal\"]//span[@class=\"price\"])[1]")
    public WebElement firstProductPrice;

    @FindBy(xpath = "(//tr[@class=\"item-info\"]//td[@class=\"col subtotal\"]//span[@class=\"price\"])[2]")
    public WebElement secondProductPrice;

}
