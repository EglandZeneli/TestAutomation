package core.elements;

import core.utilities.BaseInformation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EmptyShoppingCartElements {
    public EmptyShoppingCartElements(){
        PageFactory.initElements(BaseInformation.getDriver(), this);
    }

    @FindBy(xpath = "(//div[@class = \"actions-toolbar\"]/a[@class = \"action action-delete\"])[2]")
    public WebElement deleteButton2;

    @FindBy(xpath = "(//div[@class = \"actions-toolbar\"]/a[@class = \"action action-delete\"])[1]")
    public WebElement deleteButton1;

    @FindBy(xpath = "//span[@class = \"counter-number\"]")
    public WebElement totalShoppingCounter;


    @FindBy(xpath = "//div[@class=\"cart-empty\"]/p")
    public WebElement emptyShoppingCart;
}
