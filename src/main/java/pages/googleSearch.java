package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import utils.LocatorReader;

import java.time.Duration;
import java.util.*;

public class googleSearch {
    private WebDriver driver;
    private WebDriverWait wait;
    private LocatorReader locators;

    private By searchBox = By.name("q");
    private By suggestionList = By.xpath("//ul[@role='listbox']//li//div[@class='wM6W7d']");

    public googleSearch(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.locators = new LocatorReader("locators/loginPage.txt");

    }


    public void searchAndSelect(String query, String expectedOption) {
        // Type query
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(searchBox));
        new Actions(driver).moveToElement(input).click().sendKeys(query).perform();

        // Wait for suggestions to appear
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(suggestionList, 1));
        List<WebElement> elements = driver.findElements(suggestionList);
        List<String> suggestions = new ArrayList<>();

        // Print all suggestions
        System.out.println("🔍 Suggestions List:");
        int index = 1;
        for (WebElement el : elements) {
            String text = el.getText().trim();
            if (!text.isEmpty()) {
                suggestions.add(text);
                System.out.println(index + ". " + text);
                index++;
            }
        }

        // Check if suggestions are in ascending order
        List<String> sorted = new ArrayList<>(suggestions);
        Collections.sort(sorted, String.CASE_INSENSITIVE_ORDER);
        if (suggestions.equals(sorted)) {
            System.out.println("✅ Suggestions are in ascending order.");
        } else {
            System.out.println("⚠️ Suggestions are NOT in ascending order.");
        }

        // Select expected option
        boolean clicked = false;
        for (WebElement el : elements) {
            String actualText = el.getText().trim();
            if (actualText.equalsIgnoreCase(expectedOption)) {
                el.click();
                System.out.println("✅ Clicked on: " + actualText);
                clicked = true;
                break;
            }
        }

        if (!clicked) {
            System.out.println("❌ Option not found: " + expectedOption);
        }
    }

}