package tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import com.aventstack.extentreports.Status;

import base.BaseTest;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.SwagLab_Home_Page;
import pages.loginPage;
import utils.CredentialsUtil;
import utils.ExcelReader;
import utils.TestDataGenerator;

public class LoginTest extends BaseTest {

    @Test
    public void loginTest() {
        // Read encoded credentials from Excel
        ExcelReader excel = new ExcelReader("testdata/TestData.xlsx");
        String encodedUser = excel.getCellData("Sheet1", 1, 0);
        String encodedPass = excel.getCellData("Sheet1", 1, 1);
        String encodeurl=excel.getCellData("Sheet1", 1, 2);
        String username = CredentialsUtil.decode(encodedUser);
        String password = CredentialsUtil.decode(encodedPass);
        String url = CredentialsUtil.decode(encodeurl);

        // Navigate to app
        driver.get(url);
        test.get().log(Status.INFO, "Opening SauceDemo URL");

        // Page object usage
        loginPage loginPage = new loginPage(driver);
        loginPage.login(username, password);
        test.get().log(Status.INFO, "Logging in with provided credentials");

        // Assertion with reporting
        try {
            Assert.assertTrue(loginPage.isOnInventoryPage(), "Login failed!");
            test.get().log(Status.PASS, "Login successful - user on inventory page");
        } catch (AssertionError e) {
            test.get().log(Status.FAIL, e.getMessage());
            throw e;
        }

        // Example random test data usage
        System.out.println("Generated Email: " + TestDataGenerator.randomEmail());
    }
}