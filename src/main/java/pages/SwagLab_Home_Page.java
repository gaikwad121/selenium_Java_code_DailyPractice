package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.LocatorReader;
import utils.commonMethod;

public class SwagLab_Home_Page {
    private WebDriver driver; 
    private LocatorReader locators;
    private commonMethod cm;

    // Locator for cart count / icon
    private By cartCount;

    public SwagLab_Home_Page(WebDriver driver) {
        this.driver = driver;
        this.locators = new LocatorReader("locators.txt"); 
        this.cm = new commonMethod(driver);

        // Initialize cartCount locator
        this.cartCount = By.xpath(locators.get("addcart")); // "cartCount" key in locators.txt
    }

    private By addToCart() {
        return By.xpath(locators.get("addcart"));
    }

    public void addToCartClk() {
        cm.waitForElementClickable(addToCart(), 60);
        driver.findElement(addToCart()).click();
    }

    public boolean isCartUpdated() {
        cm.waitForElementVisible(cartCount, 60); // wait until cart icon visible
        String cartText = driver.findElement(cartCount).getText();
        return !cartText.isEmpty() && !cartText.equals("0");
    }
}
