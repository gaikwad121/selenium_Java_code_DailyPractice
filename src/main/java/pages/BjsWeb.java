package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import utils.LocatorReader;

import java.time.Duration;
import java.util.List;                        

public class BjsWeb{
    private WebDriver driver;
    private LocatorReader locators;
  private ExtentTest test;

  public BjsWeb(WebDriver driver, ExtentTest test) {
	    this.driver = driver;
	    this.test = test;
	    this.locators = new LocatorReader("locators/loginPage.txt");
	}

	public void SearchboxSendtext() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    try {
	        String actualTitle = driver.getTitle();
	        String expectedTitle = "BJ's Wholesale Club - Low Prices from Leading Brands";

	        if (actualTitle.equals(expectedTitle)) {
	            test.log(Status.PASS, "Title matched as expected");
	        } else {
	            test.log(Status.WARNING, "Title mismatch. Actual: " + actualTitle);
	        }

	        WebElement search = wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("/html[1]/body[1]/div[1]/nav[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/input[1]")
	        ));

	        js.executeScript("arguments[0].scrollIntoView(true);", search);

	        search.clear();
	        search.sendKeys("biscuits");
	        test.log(Status.INFO, "Search text entered using sendKeys");

	        if (!search.getAttribute("value").contains("biscuits")) {
	            js.executeScript("arguments[0].value='biscuits'; arguments[0].dispatchEvent(new Event('input'));", search);
	            test.log(Status.INFO, "Search text entered via JS fallback");
	        }

	        List<WebElement> list = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
	            By.cssSelector(".list.list-group a")
	        ));

	        for (WebElement li : list) {
	            System.out.println(li.getText());
	            test.log(Status.INFO, "Search suggestion: " + li.getText());
	        }

	    } catch (Exception e) {
	        test.log(Status.FAIL, "SearchboxSendtext failed: " + e.getMessage());
	        Assert.fail("SearchboxSendtext encountered an error", e);
	    }
	}}
