	package tests;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import base.BaseTest;
import pages.BjsWeb;
import utils.ExtentManager;
import utils.TestListener;

@Listeners({TestListener.class})
public class BjsWebTest extends BaseTest {

    @Test
    public void BjsWebSite() {
        ExtentTest test = ExtentManager.getInstance().createTest("BjsWebSite Test");

        try {
            driver.get("https://www.bjs.com/");
            test.log(Status.INFO, "Opening BJ's Wholesale Club URL");

            BjsWeb wb = new BjsWeb(driver, test);
            wb.SearchboxSendtext();

        } catch (Exception e) {
            test.log(Status.FAIL, "Test failed: " + e.getMessage());
            Assert.fail("Test execution failed", e);
        }
    }
}
