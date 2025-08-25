package utils;

import java.time.Duration;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class commonMethod {

	private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;
   private int timeoutInSeconds;
    public commonMethod(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
    }
public Timeouts implicitWait(By locator,int timeoutInSeconds) {
   return driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

}
    public WebElement waitForElementVisible(By locator,int timeoutInSeconds) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForElementClickable(By locator,int timeoutInSeconds) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public boolean waitForUrlContains(String text,int timeoutInSeconds) {
        return wait.until(ExpectedConditions.urlContains(text));
    }

    public boolean waitForTitleContains(String title,int timeoutInSeconds) {
        return wait.until(ExpectedConditions.titleContains(title));
    }

private FluentWait<WebDriver> getFluentWait(int timeout, int polling) {
    return new FluentWait<>(driver)
            .withTimeout(Duration.ofSeconds(timeout))
            .pollingEvery(Duration.ofMillis(polling))
            .ignoring(NoSuchElementException.class);
}

// ===== FluentWait Methods =====

// Wait until element is present in DOM
public WebElement waitForElementPresent(By locator, int timeout, int polling) {
    return getFluentWait(timeout, polling).until(driver -> driver.findElement(locator));
}

// Wait until element is visible
public WebElement waitForElementVisible(By locator, int timeout, int polling) {
    return getFluentWait(timeout, polling)
            .until(ExpectedConditions.visibilityOfElementLocated(locator));
}

// Wait until element is clickable
public WebElement waitForElementClickable(By locator, int timeout, int polling) {
    return getFluentWait(timeout, polling)
            .until(ExpectedConditions.elementToBeClickable(locator));
}

// Wait until element text is present
public boolean waitForTextPresent(By locator, String text, int timeout, int polling) {
    return getFluentWait(timeout, polling)
            .until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
}

// Wait until URL contains specific text
public boolean waitForUrlContains(String text, int timeout, int polling) {
    return getFluentWait(timeout, polling)
            .until(ExpectedConditions.urlContains(text));
}

// Wait until Title contains specific text
public boolean waitForTitleContains(String title, int timeout, int polling) {
    return getFluentWait(timeout, polling)
            .until(ExpectedConditions.titleContains(title));
}

// Wait for custom condition (flexible usage)
public <T> T waitForCondition(Function<WebDriver, T> condition, int timeout, int polling) {
    return getFluentWait(timeout, polling).until(condition);
}
public String getTitle() {
    return js.executeScript("return document.title;").toString();
}

// Refresh page
public void refreshPage() {
    js.executeScript("history.go(0);");
}

// ================= ELEMENT ACTIONS =================

// Click element
public void click(WebElement element) {
    js.executeScript("arguments[0].click();", element);
}

// Set value into input field
public void setValue(WebElement element, String value) {
    js.executeScript("arguments[0].value='" + value + "';", element);
}

// Get value of input field
public String getValue(WebElement element) {
    return js.executeScript("return arguments[0].value;", element).toString();
}

// Highlight element
public void highlight(WebElement element) {
    js.executeScript("arguments[0].style.border='3px solid red'", element);
}

// ================= SCROLLING =================

// Scroll to element
public void scrollToElement(WebElement element) {
    js.executeScript("arguments[0].scrollIntoView(true);", element);
}

// Scroll down by pixels
public void scrollDown(int pixels) {
    js.executeScript("window.scrollBy(0," + pixels + ");");
}

// Scroll up by pixels
public void scrollUp(int pixels) {
    js.executeScript("window.scrollBy(0,-" + pixels + ");");
}

// Scroll to bottom
public void scrollToBottom() {
    js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
}

// Scroll to top
public void scrollToTop() {
    js.executeScript("window.scrollTo(0, 0);");
}

// ================= PAGE INFO =================

// Get page inner text
public String getPageInnerText() {
    return js.executeScript("return document.documentElement.innerText;").toString();
}

// Zoom page
public void zoomPage(int percentage) {
    js.executeScript("document.body.style.zoom='" + percentage + "%'");
}

// ================= ALERT =================

// Generate alert
public void generateAlert(String message) {
    js.executeScript("alert('" + message + "');");
}

// ================= CUSTOM =================

// Execute custom JS
public Object executeCustomScript(String script, Object... args) {
    return js.executeScript(script, args);
}
}


