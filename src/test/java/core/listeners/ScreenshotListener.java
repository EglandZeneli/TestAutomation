package core.listeners;

import core.utilities.BaseInformation;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = BaseInformation.getDriver(); // Ensure your test class extends BaseTest
        if (driver != null) {
            TakesScreenshot screenshotTaker = (TakesScreenshot) driver;
            File screenshotFile = screenshotTaker.getScreenshotAs(OutputType.FILE);

            // Create a timestamp and file name for the screenshot
            String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            String testName = result.getName();
            String fileName = "screenshots/" + testName + "_" + timestamp + ".png";
            try {
                FileUtils.copyFile(screenshotFile, new File(fileName));
                System.out.println("Screenshot saved to: " + fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Other listener methods can be left empty or implemented as needed
    @Override public void onTestStart(ITestResult result) { }
    @Override public void onTestSuccess(ITestResult result) { }
    @Override public void onTestSkipped(ITestResult result) { }
    @Override public void onTestFailedButWithinSuccessPercentage(ITestResult result) { }
    @Override public void onStart(ITestContext context) { }
    @Override public void onFinish(ITestContext context) { }
}
