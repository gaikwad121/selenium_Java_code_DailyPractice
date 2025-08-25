package utils;

import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.WebDriver;
import base.BaseTest;

public class TestListener extends BaseTest implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = BaseTest.driver;
        String screenshotPath = ScreenshotUtil.captureScreenshot(driver, result.getName(), "FAIL");

        try {
            test.get().fail("❌ Test Failed: " + result.getThrowable())
                    .addScreenCaptureFromPath(screenshotPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        WebDriver driver = BaseTest.driver;
        String screenshotPath = ScreenshotUtil.captureScreenshot(driver, result.getName(), "PASS");

        try {
            test.get().log(Status.PASS, "✅ Test Passed")
                    .addScreenCaptureFromPath(screenshotPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().skip("⚠️ Test Skipped: " + result.getThrowable());
    }

    @Override
    public void onStart(ITestContext context) {}
    @Override
    public void onFinish(ITestContext context) {}
}
