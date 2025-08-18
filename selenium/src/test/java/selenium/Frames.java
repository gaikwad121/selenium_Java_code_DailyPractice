
package selenium;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Frames extends baseClass {

    @Test
    void case1() throws InterruptedException {
        WebElement frame1 = driver.findElement(By.xpath("//frame[@src='frame_2.html']"));
        driver.switchTo().frame(frame1);
        driver.findElement(By.name("mytext2")).sendKeys("Hello");

        driver.switchTo().parentFrame();
        WebElement frame2 = driver.findElement(By.xpath("//frame[@src='frame_1.html']"));
        driver.switchTo().frame(frame2);
        driver.findElement(By.name("mytext1")).sendKeys("Hello");

        driver.switchTo().defaultContent();
        WebElement frame3 = driver.findElement(By.xpath("//frame[@src='frame_3.html']"));
        driver.switchTo().frame(frame3);
        driver.findElement(By.name("mytext3")).sendKeys("Hello");

        driver.switchTo().frame(0);
        WebElement t = driver.findElement(By.xpath("//div[@id='i6']//*[@class='AB7Lab Id5V1']"));
        t.click();

        List<WebElement> check = driver.findElements(By.xpath("//div[@class='PkgjBf MbhUzd']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        for (WebElement b : check) {
            js.executeScript("arguments[0].scrollIntoView(true);", b);
            js.executeScript("arguments[0].click();", b);
        }

        Thread.sleep(500);
        WebElement clk = driver.findElement(By.xpath("//div[@class='mbHMhf']"));
        js.executeScript("arguments[0].click();", clk);

        WebElement drop = driver.findElement(By.xpath("//span[text()='Yes']"));
        js.executeScript("arguments[0].click();", drop);
        js.executeScript("arguments[0].click();", drop);

        WebElement e = driver.findElement(By.xpath("//span[text()='Next']"));
        js.executeScript("arguments[0].click();", e);
    }

    @Test
    void test2() throws InterruptedException {
        WebElement frame4 = driver.findElement(By.xpath("//frame[@src='frame_4.html']"));
        driver.switchTo().frame(frame4);
        driver.findElement(By.name("mytext4")).sendKeys("Hello");

        
    }
    
    
    @Test
    void test3() {
        // Step 1: Switch to frame_5
        WebElement frame5 = driver.findElement(By.xpath("//frame[@src='frame_5.html']"));
        driver.switchTo().frame(frame5);

        // Step 2: Type inside the text field in frame_5
        driver.findElement(By.name("mytext5")).sendKeys("hello");

        // Step 3: Click the "a9t9" link in frame_5
        WebElement link = driver.findElement(By.partialLinkText("a9t9"));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", link);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", link);

        // Save the original window handle
        String originalWindow = driver.getWindowHandle();

        // Step 4: Handle navigation
        try {
            // Wait for new window/tab if it opens
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.numberOfWindowsToBe(2));

            // If a new window is opened → switch to it
            for (String windowHandle : driver.getWindowHandles()) {
                if (!windowHandle.equals(originalWindow)) {
                    driver.switchTo().window(windowHandle);
                    break;
                }
            }
        } catch (TimeoutException e) {
            // No new window → still inside frame_5, wait for content update
            driver.switchTo().defaultContent();
            driver.switchTo().frame(frame5);
        }

        // Step 5: Now interact with the logo link (in new page or new tab)
        WebElement logoLink = new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.elementToBeClickable(By.id("logo")));
        //logoLink.click();
System.out.println(logoLink.isDisplayed());
        // Step 6: Clean up → return to the main page
        driver.switchTo().defaultContent();
    }}
