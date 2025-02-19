package core.elements;

import core.utilities.BaseInformation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CheckPageFiltersElements {

    public CheckPageFiltersElements(){
        PageFactory.initElements(BaseInformation.getDriver(), this);
    }
    @FindBy(xpath = "//a[@id=\"ui-id-4\"]/span[text()=\"Women\"]")
    public WebElement womenDropdown;

    @FindBy(xpath = "//a[@id=\"ui-id-9\"]/span[text()=\"Tops\"]")
    public WebElement topsDropdown;

    @FindBy(xpath = "//a[@id=\"ui-id-11\"]/span[text()=\"Jackets\"]")
    public WebElement jacketsDropdown;

    @FindBy(xpath = "//div[@class='filter-options-title' and text()='Color']")
    public WebElement colorPanel;

    @FindBy(xpath = "//div[@class='swatch-option color ' and @option-label='Red' and contains(@style,'#ff0000')]")
    public WebElement redColorJackets;

    @FindBy(xpath = "//li[contains(@class,'product-item')]")
    public List<WebElement> productItems;

    @FindBy(xpath = "//li[contains(@class,'product-item')]//div[@class='swatch-option color selected' and @option-label='Red']")
    public List<WebElement> redSwatches;

    @FindBy(xpath = "//div[@class='filter-options-title' and text()='Price']")
    public WebElement pricePanel;

    @FindBy(xpath = "//div[@data-role='content']//li[@class='item']//span[@class='price' and text() = \"$50.00\"]")
    public WebElement wantedPrice;

    @FindBy(xpath = "//span[@class=\"price\"]")
    public List<WebElement> cardsPriceRange;

    @FindBy(xpath = "//p[@class='toolbar-amount' and @id='toolbar-amount']//span[@class='toolbar-number']")
    public WebElement totalCount;

}
