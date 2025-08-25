package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;

public class ScreenshotUtil {

    public static String captureScreenshot(WebDriver driver, String testName, String status) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String folderPath = System.getProperty("user.dir") + "/screenshots/";
        File folder = new File(folderPath);
        if (!folder.exists()) folder.mkdirs();

        String fileName = testName + "_" + status + "_" + timestamp + ".png";
        String fullPath = folderPath + fileName;

        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(src, new File(fullPath));
        } catch (IOException e) {
            System.out.println("Screenshot capture failed: " + e.getMessage());
        }

        // ✅ Absolute path for ExtentReports
        return fullPath;
    }
}
