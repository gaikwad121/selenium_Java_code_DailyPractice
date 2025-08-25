package utils;

import base.BaseTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AlertUtil {

    private WebDriver driver;
    private int defaultTimeout = 10; // seconds

    public AlertUtil(WebDriver driver) {
        this.driver = driver;
    }

    // ================= JS Alerts =================

    public void acceptAlert() {
        handleAlert("accept", null);
    }

    public void dismissAlert() {
        handleAlert("dismiss", null);
    }

    public void sendKeysToAlert(String text) {
        handleAlert("sendKeys", text);
    }

    private void handleAlert(String action, String inputText) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(defaultTimeout));
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());

            String alertText = alert.getText();
            switch (action) {
                case "accept":
                    alert.accept();
                    BaseTest.test.get().log(Status.INFO, "✅ Accepted alert: " + alertText);
                    break;
                case "dismiss":
                    alert.dismiss();
                    BaseTest.test.get().log(Status.INFO, "⚠️ Dismissed alert: " + alertText);
                    break;
                case "sendKeys":
                    alert.sendKeys(inputText);
                    alert.accept();
                    BaseTest.test.get().log(Status.INFO, "✏️ Sent text to alert: " + inputText);
                    break;
            }

            // Screenshot after handling alert
            String screenshot = ScreenshotUtil.captureScreenshot(driver, "AlertAction", action.toUpperCase());
            BaseTest.test.get().addScreenCaptureFromPath(screenshot);

        } catch (Exception e) {
            BaseTest.test.get().log(Status.WARNING, "No alert present: " + e.getMessage());
        }
    }

    
    public void acceptHtmlModal(By okButtonLocator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(defaultTimeout));
            wait.until(ExpectedConditions.elementToBeClickable(okButtonLocator)).click();
            BaseTest.test.get().log(Status.INFO, "✅ Clicked OK on HTML modal");

            // Screenshot after action
            String screenshot = ScreenshotUtil.captureScreenshot(driver, "HTMLModal", "ACCEPT");
            BaseTest.test.get().addScreenCaptureFromPath(screenshot);

        } catch (Exception e) {
            BaseTest.test.get().log(Status.WARNING, "HTML modal not present: " + e.getMessage());
        }
    }

}
