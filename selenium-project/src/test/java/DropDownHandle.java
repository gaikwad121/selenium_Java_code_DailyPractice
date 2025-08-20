import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.*;

public class DropDownHandle extends baseClass {



    @Test
    public void verifyDropdownIsInAscendingOrder() {
        WebElement dropdownElement = driver.findElement(By.id("country"));
        Select sel = new Select(dropdownElement);

        List<String> originalList = new ArrayList<>();
        for (WebElement option : sel.getOptions()) {
            originalList.add(option.getText().trim());
        }

        List<String> sortedList = new ArrayList<>(originalList);
        Collections.sort(sortedList);

       // Assert.assertEquals(originalList, sortedList, "Dropdown is NOT in ascending order.");
        if(originalList.equals(sortedList)) {
        	System.out.println("DropDown in a ascending order");
        }else {
        	System.out.println("DropDown are not in ascending order");

        }
    }
    @Test
    void AscendingOrderOrNot() {
    	WebElement selectCountryList = driver.findElement(By.id("country-list"));
    	Select sel = new Select(selectCountryList);

    	// Step 1: Extract all dropdown options into a list
    	List<String> originalList = new ArrayList<>();
    	for (WebElement option : sel.getOptions()) {
    	    originalList.add(option.getText().trim());
    	}

    	// Step 2: Create a sorted copy of the original list
    	List<String> sortedList = new ArrayList<>(originalList);
    	Collections.sort(sortedList);

    	// Step 3: Compare both lists
    	if (originalList.equals(sortedList)) {
    	    System.out.println("Dropdown is in ascending order");
    	} else {
    	    System.out.println("Dropdown is NOT in ascending order");
    	}

    	// Step 4: Print main menu items
    	List<WebElement> li = driver.findElements(By.xpath("//ul[@class='main-menu']/li/a"));
    	for (WebElement original : li) {
    	    System.out.println(original.getText().trim());
    	}
    }
    @Test
    void selectOption() {
    	WebElement selectCountryList = driver.findElement(By.id("country-list"));
    	Select sel = new Select(selectCountryList);
 sel.selectByContainsVisibleText("Braz");
    }
    @AfterClass
    public void tearDown() {
        //driver.quit();
    }
}