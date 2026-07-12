package utility;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class ScreenshotUtil {

    public static void captureScreenshot(WebDriver driver, String fileName) {

        // Define path
        String path = System.getProperty("user.dir") + "\\screenshots\\" + fileName + ".png";

        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File dest = new File(path);

        // Create folder if it doesn't exist
        dest.getParentFile().mkdirs();

        try {
            FileUtils.copyFile(src, dest);
            System.out.println("Screenshot saved at: " + path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}