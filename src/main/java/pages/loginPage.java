package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.LocatorReader;

public class loginPage {
    private WebDriver driver;
    private LocatorReader locators;

    public loginPage(WebDriver driver) {
        this.driver = driver;
        this.locators = new LocatorReader("locators/loginPage.txt");
    }

    // Elements
    private By usernameField() {
        return By.name(locators.get("username"));
    }

    private By passwordField() {
        return By.name(locators.get("password"));
    }

    private By loginButton() {
        return By.id(locators.get("login-button"));
    }

    // Actions
    public void enterUsername(String username) {
        driver.findElement(usernameField()).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField()).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(loginButton()).click();
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    // Validation
    public boolean isOnInventoryPage() {
        return driver.getCurrentUrl().contains("inventory.html");
    }
}
