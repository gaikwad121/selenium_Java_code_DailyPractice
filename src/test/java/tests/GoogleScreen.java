package tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.apache.commons.math3.optim.nonlinear.scalar.LineSearch;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import com.aventstack.extentreports.Status;

import base.BaseTest;
import pages.googleSearch;
import utils.TestListener;
@Listeners({TestListener.class})

public class GoogleScreen extends BaseTest {

    private googleSearch search;

    @BeforeMethod
    public void setUpPage() {
        search = new googleSearch(driver); // driver is now initialized by BaseTest
    }

    @Test
    void Test2() {
       // driver.get("https://www.google.com/");
    	driver.get("https://www.google.com/?zx=1756054088138&no_sw_cr=1");
        test.get().log(Status.INFO, "Opening Google URL");

        googleSearch google = new googleSearch(driver);
        google.searchAndSelect("selenium", "selenium interview questions");

        test.get().log(Status.INFO, "The has copmleted");
   

        //search.pressEnter();

      //  String title = search.getPageTitle();
        //test.get().log(Status.INFO, "Page title is: " + title);
    }
	@AfterTest
	public void tearDown() {
    	driver.close();
    }
}
