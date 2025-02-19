package core.utilities;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.concurrent.TimeUnit;


public class BaseInformation {

    public static BaseInformation getBaseInformation(){
       return new BaseInformation();
    }
    private static WebDriver driver;
    public static WebDriver getDriver(){
        if(driver == null){
            String browserType = ConfigurationReader.getProperty("browser").toLowerCase();
//            String browserType = Globals.browserType.toLowerCase();
            switch (browserType){
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                  //  System.setProperty("webdriver.chrome.driver","src/test/resources/drivers/chromedriver.exe");
                    driver = new ChromeDriver();
                    driver.manage().window().maximize();
                    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    break;
                case "firefox":
                    System.setProperty("","");
                    driver = new FirefoxDriver();
                    driver.manage().window().maximize();
                    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    break;
            }
        }
        return driver;
    }

    public static void quit() {
       driver.quit();
    }

    public static void setDriver() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        BaseInformation.driver = driver;
    }


}