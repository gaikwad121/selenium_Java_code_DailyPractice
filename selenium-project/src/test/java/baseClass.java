
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class baseClass {
    static WebDriver driver;

    @BeforeMethod
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(); 
        //driver.get("https://ui.vision/demo/webtest/frames/");
        driver.get("https://testautomationpractice.blogspot.com/");
        driver.get("https://phppot.com/demo/jquery-dependent-dropdown-list-countries-and-states/");
        driver.manage().window().maximize();
    }
}