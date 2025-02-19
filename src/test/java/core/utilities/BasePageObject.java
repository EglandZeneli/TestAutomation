package core.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import java.time.Duration;

public class BasePageObject {
    private ElementLocatorFactory rootFactory;
    private final Duration defaultDuration = Duration.ofMillis(2);
    private BaseInformation baseInformation;
    private WaitUtils waitUtils;
    private WebElementUtils webElementUtils;

    private final WebDriver driver = BaseInformation.getDriver();


    public BasePageObject(BaseInformation baseInformation) {
        this.baseInformation = baseInformation;
        rootFactory = new DefaultElementLocatorFactory(driver);
        PageFactory.initElements(rootFactory, this);
    }

    public BasePageObject(BaseInformation baseInformation, By locator) {
        this.baseInformation = baseInformation;
        try{
            getWaitUtils().waitForElementVisible(locator);
        } catch (Exception ex) {

        }
        rootFactory = new DefaultElementLocatorFactory(
                driver.findElement(locator));
        PageFactory.initElements(rootFactory, this);
    }

    public BasePageObject(BaseInformation baseInformation, WebElement rootElement) {
        this.baseInformation = baseInformation;
        rootFactory = new DefaultElementLocatorFactory(rootElement);
        PageFactory.initElements(rootFactory, this);
    }

    public void getUrl(String url){
        driver.get(url);
    }

    public WaitUtils getWaitUtils() {
        if (waitUtils == null) {
            waitUtils = new WaitUtils(getBaseInformation(), defaultDuration);
        }
        return waitUtils;
    }

    public WebElementUtils getWebElementUtils() {
        if (webElementUtils == null) {
            webElementUtils = new WebElementUtils(getBaseInformation(), defaultDuration);
        }
        return webElementUtils;
    }

    public BaseInformation getBaseInformation() {
        return baseInformation;
    }
}
